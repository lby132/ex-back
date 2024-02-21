package com.example.back.exback.api.controller.item.request;

import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.item.ItemSellingStatus;
import com.example.back.exback.domain.item.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequest {

    @NotNull(message = "상품 타입은 필수입니다.")
    private ItemType type;

    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ItemSellingStatus sellingStatus;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;

    @Positive(message = "상품 가격은 양수여야 합니다.")
    private int price;

    private int sale = 0;

    @Builder
    public ItemRequest(ItemType type, ItemSellingStatus sellingStatus, String name, int price, int sale) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
        this.sale = sale;
    }

    public Item toEntity() {
        return Item.builder()
                .type(type)
                .sellingStatus(sellingStatus)
                .itemName(name)
                .price(price)
                .sale(sale)
                .build();
    }
}
