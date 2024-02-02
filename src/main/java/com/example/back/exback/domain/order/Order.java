package com.example.back.exback.domain.order;

import com.example.back.exback.domain.BaseEntity;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.orderdetail.OrderDetail;
import com.example.back.exback.domain.orderitem.OrderItem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;
    private LocalDateTime orderDate;
    private int itemCnt;
    private String itemName;

    @Builder
    public Order(Member member, List<Item> items, OrderStatus orderStatus, LocalDateTime orderDate) {
        this.member = member;
        this.orderItems = items
                .stream()
                .map(item -> new OrderItem(this, item))
                .collect(Collectors.toList());
        this.orderStatus = orderStatus;
        this.totalPrice = calculatorTotalPrice(items);
        this.orderDate = orderDate;
    }

    private int calculatorTotalPrice(List<Item> items) {
        return items.stream()
                .mapToInt(Item::getPrice)
                .sum();
    }

    public static Order createOrder(Member member, List<Item> items) {
        return Order.builder()
                .member(member)
                .orderStatus(OrderStatus.INIT)
                .items(items)
                .orderDate(LocalDateTime.now())
                .build();
    }
}
