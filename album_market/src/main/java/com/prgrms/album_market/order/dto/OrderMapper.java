package com.prgrms.album_market.order.dto;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.order.dto.response.CreateOrderResponse;
import com.prgrms.album_market.order.dto.response.GetOrderResponse;
import com.prgrms.album_market.order.entity.Order;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OrderMapper {
    public static Order toOrder(Member member, Album album, Integer quantity) {
        return Order.builder()
                .member(member)
                .album(album)
                .quantity(quantity)
                .build();
    }

    public static CreateOrderResponse toCreateOrderResponse(Order order) {
        return new CreateOrderResponse(order.getId());
    }

    public static GetOrderResponse toGetOrderResponse(Order order) {
        return GetOrderResponse.builder()
                .orderId(order.getId())
                .albumId(order.getAlbum().getId())
                .albumTitle(order.getAlbum().getTitle())
                .memberId(order.getMember().getId())
                .memberName(order.getMember().getName())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus().toString())
                .build();
    }
}
