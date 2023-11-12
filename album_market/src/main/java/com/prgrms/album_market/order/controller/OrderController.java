package com.prgrms.album_market.order.controller;

import com.prgrms.album_market.common.PageResponse;
import com.prgrms.album_market.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prgrms.album_market.order.dto.OrderRequest.CreateOrderReq;
import static com.prgrms.album_market.order.dto.OrderResponse.CreateOrderRes;
import static com.prgrms.album_market.order.dto.OrderResponse.GetOrderRes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "주문 생성")
    @PostMapping("/{memberId}")
    public ResponseEntity<CreateOrderRes> createOrder(@PathVariable Long memberId, @Valid @RequestBody CreateOrderReq request) {
        CreateOrderRes response = orderService.createOrder(memberId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "모든 주문 조회")
    @GetMapping
    public ResponseEntity<PageResponse<GetOrderRes>> getOrders(Pageable pageable) {
        PageResponse<GetOrderRes> response = orderService.getOrders(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 회원 주문 조회")
    @GetMapping("/{memberId}")
    public ResponseEntity<PageResponse<GetOrderRes>> getMemberOrders(@PathVariable Long memberId, Pageable pageable) {
        PageResponse<GetOrderRes> response = orderService.getMemberOrders(memberId, pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 업데이트")
    @PostMapping("cancel/{orderId}")
    public ResponseEntity<GetOrderRes> cancelOrder(@PathVariable Long orderId) {
        GetOrderRes response = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "배달 완료", description = "주문 상태 배달 완료로 변경")
    @PostMapping("deliver/{orderId}")
    public ResponseEntity<GetOrderRes> deliverOrder(@PathVariable Long orderId) {
        GetOrderRes response = orderService.deliverOrder(orderId);
        return ResponseEntity.ok(response);
    }
}
