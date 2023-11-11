package com.prgrms.album_market.order.controller;

import com.prgrms.album_market.common.PageResponse;
import com.prgrms.album_market.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<PageResponse<GetOrderRes>> getOrders(Pageable pageable) {
        PageResponse<GetOrderRes> response = orderService.getOrders(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<PageResponse<GetOrderRes>> getMemberOrders(@PathVariable Long memberId, Pageable pageable) {
        PageResponse<GetOrderRes> response = orderService.getMemberOrders(memberId, pageable);
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
