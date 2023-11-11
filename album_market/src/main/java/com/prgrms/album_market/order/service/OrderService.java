package com.prgrms.album_market.order.service;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.service.AlbumService;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import com.prgrms.album_market.order.entity.Order;
import com.prgrms.album_market.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prgrms.album_market.order.dto.OrderMapper.*;
import static com.prgrms.album_market.order.dto.OrderRequest.CreateOrderReq;
import static com.prgrms.album_market.order.dto.OrderResponse.*;
import static com.prgrms.album_market.order.dto.OrderResponse.CreateOrderRes;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final AlbumService albumService;
    public ResponseEntity<CreateOrderRes> createOrder(Long memberId, CreateOrderReq request) {
        Member member = memberService.getMemberEntity(memberId);
        Album album = albumService.getAlbumEntity(request.getAlbumId());
        int totalPrice = album.getPrice() * request.getQuantity();
        int quantity = request.getQuantity();

        album.reduceStock(quantity);
        Order order = toOrder(member, album, quantity, totalPrice);
        orderRepository.save(order);

        return ResponseEntity.ok(toCreateOrderRes(order));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<GetOrderListRes> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(toGetOrderListRes(orders));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<GetOrderListRes> getMemberOrders(Long memberId) {
        Member member = memberService.getMemberEntity(memberId);
        List<Order> memberOrders = orderRepository.findByMember(member);
        return ResponseEntity.ok(toGetOrderListRes(memberOrders));
    }
}
