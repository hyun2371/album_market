package com.prgrms.album_market.order.dto.response;

import lombok.Builder;

@Builder
public record GetOrderResponse (
    Long orderId,
    Long albumId,
    String albumTitle,
    Long memberId,
    String memberName,
    Integer quantity,
    int totalPrice,
    String orderStatus
){}
