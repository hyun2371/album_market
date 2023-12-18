package com.prgrms.album_market.order.service;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.service.AlbumService;
import com.prgrms.album_market.common.PageResponse;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.common.exception.ErrorCode;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import com.prgrms.album_market.order.dto.OrderMapper;
import com.prgrms.album_market.order.dto.request.CreateOrderRequest;
import com.prgrms.album_market.order.dto.response.CreateOrderResponse;
import com.prgrms.album_market.order.dto.response.GetOrderResponse;
import com.prgrms.album_market.order.entity.Order;
import com.prgrms.album_market.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.prgrms.album_market.order.dto.OrderMapper.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final AlbumService albumService;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        Member member = memberService.getMemberEntity(request.memberId());
        Album album = albumService.getAlbumEntity(request.albumId());
        Order order = toOrder(member, album, request.quantity());
        orderRepository.save(order);

        return toCreateOrderResponse(order);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetOrderResponse> getOrders(Pageable pageable) {
        Page<Order> pagedOrders = orderRepository.findAll(pageable);
        Page<GetOrderResponse> pagedGetOrderRes = pagedOrders.map(OrderMapper::toGetOrderResponse);
        return PageResponse.fromPage(pagedGetOrderRes);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetOrderResponse> getMemberOrders(Long memberId, Pageable pageable) {
        Member member = memberService.getMemberEntity(memberId);
        Page<Order> pagedMemberOrders = orderRepository.findByMember(member, pageable);
        Page<GetOrderResponse> pagedGetOrderRes = pagedMemberOrders.map(OrderMapper::toGetOrderResponse);

        return PageResponse.fromPage(pagedGetOrderRes);
    }

    @Transactional
    public GetOrderResponse cancelOrder(Long orderId) {
        Order order = getOrderEntity(orderId);
        order.cancelOrder();

        return toGetOrderResponse(order);
    }

    @Transactional
    public GetOrderResponse deliverOrder(Long orderId) {
        Order order = getOrderEntity(orderId);
        order.deliverOrder();

        return toGetOrderResponse(order);
    }

    @Transactional
    public Order getOrderEntity(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_ORDER_ID));
    }
}
