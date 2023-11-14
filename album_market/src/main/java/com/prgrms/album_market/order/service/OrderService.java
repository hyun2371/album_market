package com.prgrms.album_market.order.service;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.service.AlbumService;
import com.prgrms.album_market.common.PageResponse;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.common.exception.ErrorCode;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import com.prgrms.album_market.order.dto.OrderMapper;
import com.prgrms.album_market.order.entity.Order;
import com.prgrms.album_market.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.prgrms.album_market.order.dto.OrderMapper.*;
import static com.prgrms.album_market.order.dto.OrderRequest.CreateOrderReq;
import static com.prgrms.album_market.order.dto.OrderResponse.CreateOrderRes;
import static com.prgrms.album_market.order.dto.OrderResponse.GetOrderRes;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final AlbumService albumService;

    public CreateOrderRes createOrder(CreateOrderReq request) {
        Member member = memberService.getMemberEntity(request.getMemberId());
        Album album = albumService.getAlbumEntity(request.getAlbumId());
        Order order = toOrder(member, album, request.getQuantity());

        orderRepository.save(order);

        return toCreateOrderRes(order);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetOrderRes> getOrders(Pageable pageable) {
        Page<Order> pagedOrders = orderRepository.findAll(pageable);
        Page<GetOrderRes> pagedGetOrderRes = pagedOrders.map(OrderMapper::toGetOrderRes);
        return PageResponse.fromPage(pagedGetOrderRes);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetOrderRes> getMemberOrders(Long memberId, Pageable pageable) {
        Member member = memberService.getMemberEntity(memberId);
        Page<Order> pagedMemberOrders = orderRepository.findByMember(member, pageable);
        Page<GetOrderRes> pagedGetOrderRes = pagedMemberOrders.map(OrderMapper::toGetOrderRes);

        return PageResponse.fromPage(pagedGetOrderRes);
    }

    public GetOrderRes cancelOrder(Long orderId) {
        Order order = getOrderEntity(orderId);
        order.cancelOrder();

        return toGetOrderRes(order);
    }

    public GetOrderRes deliverOrder(Long orderId) {
        Order order = getOrderEntity(orderId);
        order.deliverOrder();

        return toGetOrderRes(order);
    }

    public Order getOrderEntity(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_ORDER_ID));
    }
}
