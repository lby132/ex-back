package com.example.back.exback.domain.stock;

import com.example.back.exback.api.exception.NotEnoughStockException;
import com.example.back.exback.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    private String itemName;

    private int quantity;

    @Builder
    public Stock(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public static Stock create(String itemName, int quantity) {
        return Stock.builder()
                .itemName(itemName)
                .quantity(quantity)
                .build();
    }

    public boolean isQuantityLessThan(int quantity) {
        return this.quantity < quantity;
    }

    public void deductQuantity(int quantity) {
        if (isQuantityLessThan(quantity)) {
            throw new NotEnoughStockException("재고 수량이 부족합니다.");
        }
        this.quantity = quantity;
    }
}
