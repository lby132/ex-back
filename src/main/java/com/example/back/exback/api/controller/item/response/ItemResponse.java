package com.example.back.exback.api.controller.item.response;

import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.item.ItemSellingStatus;
import com.example.back.exback.domain.item.ItemType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponse {

    private String name;
    private String itemDescription;
    private ItemType type;
    private ItemSellingStatus sellingStatus;
    private int price;
    private int sale;
    private int stockQuantity;

    @Builder
    public ItemResponse(String name, String itemDescription, ItemType type, ItemSellingStatus sellingStatus, int price, int sale, int stockQuantity) {
        this.name = name;
        this.itemDescription = itemDescription;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.price = price;
        this.sale = sale;
        this.stockQuantity = stockQuantity;
    }

    public static ItemResponse of(Item item) {
        return ItemResponse.builder()
                .name(item.getItemName())
                .itemDescription(item.getItemDescription())
                .type(item.getType())
                .sellingStatus(item.getSellingStatus())
                .price(item.getPrice())
                .sale(item.getSale())
                .build();
    }
}
