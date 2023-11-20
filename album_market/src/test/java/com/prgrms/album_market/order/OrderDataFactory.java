package com.prgrms.album_market.order;

import com.prgrms.album_market.album.AlbumDataFactory;
import com.prgrms.album_market.member.MemberDataFactory;
import com.prgrms.album_market.order.entity.Order;

public class OrderDataFactory {

    public static Order order(){
        return Order.builder()
                .member(MemberDataFactory.getMember())
                .album(AlbumDataFactory.getAlbum())
                .quantity(1)
                .build();
    }

}
