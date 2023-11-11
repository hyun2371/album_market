package com.prgrms.album_market.order.controller;

import com.prgrms.album_market.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prgrms.album_market.order.dto.OrderRequest.CreateOrderReq;
import static com.prgrms.album_market.order.dto.OrderResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{memberId}")
    public ResponseEntity<CreateOrderRes> createOrder(@PathVariable Long memberId, @Valid @RequestBody CreateOrderReq request) {
        CreateOrderRes response = orderService.createOrder(memberId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GetOrderListRes> getOrders() {
        GetOrderListRes response = orderService.getOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GetOrderListRes> getMemberOrders(@PathVariable Long memberId) {
        GetOrderListRes response = orderService.getMemberOrders(memberId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("cancel/{orderId}")
    public ResponseEntity<GetOrderRes> cancelOrder(@PathVariable Long orderId) {
        GetOrderRes response = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("deliver/{orderId}")
    public ResponseEntity<GetOrderRes> deliverOrder(@PathVariable Long orderId) {
        GetOrderRes response = orderService.deliverOrder(orderId);
        return ResponseEntity.ok(response);
    }
}
