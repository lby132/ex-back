package com.example.back.exback.domain.order;

import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.category.Category;
import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.member.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void createOrderTest() {
        // given
        List<Item> itemList = List.of(
                createItem("티셔츠", "면100%", 10000, 0, 5),
                createItem("패딩", "거위털100%", 100000, 0, 2)
        );

        // when
        Order order = Order.createOrder(createMember(), itemList);

        // then
        assertEquals(110000, order.getTotalPrice());
        assertEquals("티셔츠", order.getOrderItems().get(0).getItem().getName());
    }

    private Member createMember() {
        return Member.builder()
                .address(new Address("0444", "서울시", "27-3번지"))
                .userId("memberA")
                .userPw("1234")
                .age(24)
                .phone("01010102320")
                .gender('M')
                .regDate(LocalDateTime.now())
                .build();
    }

    private Item createItem(String name, String itemDescription, int price, int sale, int quantity) {
        Category category = new Category("의류", "100");

        final LocalDateTime now = LocalDateTime.now();
        return new Item(name, itemDescription, price, sale, quantity, now, category);
    }
}