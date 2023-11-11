package com.prgrms.album_market.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OrderResponse {
    @AllArgsConstructor @Getter
    public static class CreateOrderRes {
        private Long createdOrderId;
    }

    @AllArgsConstructor @Getter @Builder
    public static class GetOrderRes {
        private Long albumId;
        private String albumTitle;
        private Long memberId;
        private String memberName;
        private Integer quantity;
        private int totalPrice;
        private String orderStatus;
    }

    @AllArgsConstructor @Getter
    public static class GetOrderListRes {
        List<GetOrderRes> orders;
    }

}
