package com.example.back.exback.api.controller.order.response;

import com.example.back.exback.api.controller.item.response.ItemResponse;
import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.order.Order;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {

    private int totalPrice;
    private LocalDateTime regDate;
    private List<ItemResponse> items;

    @Builder
    public OrderResponse(int totalPrice, LocalDateTime regDate, List<ItemResponse> items) {
        this.totalPrice = totalPrice;
        this.regDate = regDate;
        this.items = items;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .totalPrice(order.getTotalPrice())
                .regDate(order.getOrderDate())
                .items(order.getOrderItems()
                        .stream().map(orderItem -> ItemResponse.of(orderItem.getItem()))
                        .collect(Collectors.toList()))
                .build();
    }

}
