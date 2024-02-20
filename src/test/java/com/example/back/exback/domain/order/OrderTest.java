package com.example.back.exback.domain.order;

import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.item.ItemType;
import com.example.back.exback.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.back.exback.domain.item.ItemSellingStatus.SELLING;
import static com.example.back.exback.domain.item.ItemType.TOP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void createOrderTest() {
        // given
        List<Item> itemList = List.of(
                createItem(TOP, "티셔츠", "면100%", 10000, 0, 5),
                createItem(TOP,"패딩", "거위털100%", 100000, 0, 5)
        );

        // when
        Order order = Order.createOrder(createMember(), itemList);

        // then
        assertEquals(110000, order.getTotalPrice());
        assertEquals("티셔츠", order.getOrderItems().get(0).getItem().getItemName());

    }

    @Test
    @DisplayName("주문 생성시 상태는 INIT 이다.")
    void orderStatus() {
        // given
        List<Item> itemList = List.of(
                createItem(TOP,"티셔츠", "면100%", 10000, 0, 5),
                createItem(TOP,"패딩", "거위털100%", 100000, 0, 2)
        );

        // when
        Order order = Order.createOrder(createMember(), itemList);

        // then
        assertEquals(order.getOrderStatus(), OrderStatus.INIT);
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

    private Item createItem(ItemType type, String name, String itemDescription, int price, int sale, int quantity) {

        return Item.builder()
                .type(type)
                .sellingStatus(SELLING)
                .itemName(name)
                .itemDescription(itemDescription)
                .price(price)
                .sale(sale)
                .build();
    }
}