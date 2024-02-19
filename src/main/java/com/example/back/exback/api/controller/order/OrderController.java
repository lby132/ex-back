package com.example.back.exback.api.controller.order;

import com.example.back.exback.api.ApiResponse;
import com.example.back.exback.api.controller.order.request.OrderRequest;
import com.example.back.exback.api.controller.order.response.OrderResponse;
import com.example.back.exback.api.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/v1/order/create")
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        return ApiResponse.ok(orderService.createOrder(request));
    }
}
