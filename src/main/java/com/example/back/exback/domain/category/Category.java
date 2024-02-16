package com.example.back.exback.domain.category;

import com.example.back.exback.domain.BaseEntity;
import com.example.back.exback.domain.item.Item;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;
    private String parentCode;

    @Builder
    public Category(String name, String parentCode) {
        this.name = name;
        this.parentCode = parentCode;
    }
}
