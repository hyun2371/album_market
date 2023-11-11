package com.prgrms.album_market.order.dto;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.order.entity.Order;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.prgrms.album_market.order.dto.OrderResponse.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)

public class OrderMapper {
    public static Order toOrder(Member member, Album album, Integer quantity, int totalPrice) {
        return Order.builder()
                .member(member)
                .album(album)
                .quantity(quantity)
                .totalPrice(totalPrice)
                .build();
    }

    public static CreateOrderRes toCreateOrderRes(Order order){
        return new CreateOrderRes(order.getId());
    }

    public static GetOrderRes toGetOrderRes(Order order){
        return GetOrderRes.builder()
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

    public static GetOrderListRes toGetOrderListRes(List<Order> orders){
        List<GetOrderRes> list = new ArrayList<>();
        for (Order order: orders){
            list.add(toGetOrderRes(order));
        }
        return new GetOrderListRes(list);
    }
}
