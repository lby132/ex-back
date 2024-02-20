package com.example.back.exback.domain.item;

import com.example.back.exback.domain.BaseEntity;
import com.example.back.exback.domain.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "item_id")
    private Long id;

//    @ManyToMany(mappedBy = "item")
//    private List<ItemCategory> itemCategories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Enumerated(EnumType.STRING)
    private ItemSellingStatus sellingStatus;

    private String itemName;
    private String itemDescription;
    private int price;
    private int sale;

    @Column(columnDefinition = "char default 'N'")
    private char deleted;

    @Builder
    public Item(ItemType type, ItemSellingStatus sellingStatus, String itemName, String itemDescription, int price, int sale) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.sale = sale;
    }
}
