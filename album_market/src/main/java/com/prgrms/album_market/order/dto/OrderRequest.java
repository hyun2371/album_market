package com.prgrms.album_market.order.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OrderRequest {
    @Getter
    public static class CreateOrderReq {
        private Long memberId;
        private Long albumId;
        @Min(value = 1, message = "최소 주문 수량은 1개입니다.")
        private Integer quantity;
    }
}
