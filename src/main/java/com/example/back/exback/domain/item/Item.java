package com.example.back.exback.domain.item;

import com.example.back.exback.domain.BaseEntity;
import com.example.back.exback.domain.category.Category;
import com.example.back.exback.domain.itemcategory.ItemCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ManyToMany(mappedBy = "item")
    private List<ItemCategory> itemCategories = new ArrayList<>();

    private String name;
    private String itemDescription;
    private int price;
    private int sale;
    private LocalDateTime regDt;
    private int quantity;

    @Column(columnDefinition = "char default 'N'")
    private char deleted;

    public Item(String name, String itemDescription, int price, int sale, int quantity, LocalDateTime regDt, Category... categories) {
        this.name = name;
        this.itemDescription = itemDescription;
        this.price = price;
        this.sale = sale;
        this.quantity = quantity;
        this.regDt = regDt;
        for (Category category : categories) {
            this.itemCategories.add(new ItemCategory(this, category));
        }
    }

    public void relationshipSetCategories(ItemCategory itemCategory) {
        this.itemCategories.add(itemCategory);
        itemCategory.relationshipSetItem(this);
    }
}
