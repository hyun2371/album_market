package com.prgrms.album_market.order;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.order.dto.request.CreateOrderRequest;
import com.prgrms.album_market.order.entity.Order;

public class OrderFixture {

    public static Order order(Member member, Album album, int quantity) {
        return Order.builder()
            .member(member)
            .album(album)
            .quantity(quantity)
            .build();
    }

    public static CreateOrderRequest createOrderRequest(int quantity) {
        return CreateOrderRequest.builder()
            .memberId(1L)
            .albumId(1L)
            .quantity(quantity)
            .build();
    }

}
