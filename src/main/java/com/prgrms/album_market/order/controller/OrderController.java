package com.prgrms.album_market.order.controller;

import com.prgrms.album_market.common.PageResponse;
import com.prgrms.album_market.order.dto.request.CreateOrderRequest;
import com.prgrms.album_market.order.dto.response.CreateOrderResponse;
import com.prgrms.album_market.order.dto.response.GetOrderResponse;
import com.prgrms.album_market.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "주문 생성")
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        CreateOrderResponse response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "모든 주문 조회")
    @GetMapping
    public ResponseEntity<PageResponse<GetOrderResponse>> getOrders(Pageable pageable) {
        PageResponse<GetOrderResponse> response = orderService.getOrders(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 회원 주문 조회")
    @GetMapping("/{memberId}")
    public ResponseEntity<PageResponse<GetOrderResponse>> getMemberOrders(@PathVariable Long memberId, Pageable pageable) {
        PageResponse<GetOrderResponse> response = orderService.getMemberOrders(memberId, pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "주문 취소")
    @PostMapping("cancel/{orderId}")
    public ResponseEntity<GetOrderResponse> cancelOrder(@PathVariable Long orderId) {
        GetOrderResponse response = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "배달 완료", description = "주문 상태 배달 완료로 변경")
    @PostMapping("deliver/{orderId}")
    public ResponseEntity<GetOrderResponse> deliverOrder(@PathVariable Long orderId) {
        GetOrderResponse response = orderService.deliverOrder(orderId);
        return ResponseEntity.ok(response);
    }
}
