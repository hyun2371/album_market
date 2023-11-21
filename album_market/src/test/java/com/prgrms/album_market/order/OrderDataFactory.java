package com.prgrms.album_market.order;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.order.dto.OrderRequest;
import com.prgrms.album_market.order.entity.Order;

public class OrderDataFactory {

    public static Order order(Member member, Album album, int quantity){
        return Order.builder()
                .member(member)
                .album(album)
                .quantity(quantity)
                .build();
    }

    public static OrderRequest.CreateOrderReq createOrderReq(int quantity){
        return OrderRequest.CreateOrderReq.builder()
                .memberId(1L)
                .albumId(1L)
                .quantity(quantity)
                .build();
    }

}
