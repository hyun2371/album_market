package com.prgrms.album_market.order.controller;

import com.prgrms.album_market.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prgrms.album_market.order.dto.OrderRequest.CreateOrderReq;
import static com.prgrms.album_market.order.dto.OrderResponse.*;
import static com.prgrms.album_market.order.dto.OrderResponse.CreateOrderRes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{memberId}")
    public ResponseEntity<CreateOrderRes> createOrder(@PathVariable Long memberId, @Valid @RequestBody CreateOrderReq request){
        return orderService.createOrder(memberId, request);
    }

    @GetMapping
    public ResponseEntity<GetOrderListRes> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GetOrderListRes> getMemberOrders(@PathVariable Long memberId){
        return orderService.getMemberOrders(memberId);
    }
}
