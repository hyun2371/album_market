package com.prgrms.album_market.order.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;

@Builder
public record CreateOrderRequest(
        Long memberId,
        Long albumId,
        @Min(value = 1, message = "최소 주문 수량은 1개입니다.")
        Integer quantity
) { }
