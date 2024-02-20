//package com.example.back.exback.domain.itemcategory;
//
//import com.example.back.exback.domain.BaseEntity;
//import com.example.back.exback.domain.category.Category;
//import com.example.back.exback.domain.item.Item;
//import jakarta.persistence.*;
//import lombok.*;
//
//import static jakarta.persistence.GenerationType.IDENTITY;
//
//@Entity
//@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//public class ItemCategory extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "item_category_id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "item_id")
//    public Item item;
//
//    @ManyToOne
//    @JoinColumn(name = "item_id")
//    public Category category;
//
//    @Builder
//    public ItemCategory(Item item, Category category) {
//        this.item = item;
//        this.category = category;
//    }
//
//    public void relationshipSetItem(Item item) {
//        this.item = item;
//    }
//
//    public void relationshipSetCategory(Category category) {
//        this.category = category;
//    }
//
//
//}
