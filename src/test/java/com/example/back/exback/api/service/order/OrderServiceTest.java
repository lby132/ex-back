package com.example.back.exback.api.service.order;

import com.example.back.exback.api.controller.order.request.OrderRequest;
import com.example.back.exback.api.controller.order.response.OrderResponse;
import com.example.back.exback.api.exception.NotEnoughStockException;
import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.item.ItemRepository;
import com.example.back.exback.domain.item.ItemType;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import com.example.back.exback.domain.order.OrderRepository;
import com.example.back.exback.domain.stock.Stock;
import com.example.back.exback.domain.stock.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.back.exback.domain.item.ItemSellingStatus.SELLING;
import static com.example.back.exback.domain.item.ItemType.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    @AfterEach
    void tearDown() {
        orderRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
        itemRepository.deleteAllInBatch();
        stockRepository.deleteAllInBatch();
    }


    @Test
    void orderCreate() {
        // given
        List<Item> itemList = List.of(
                createItem(TOP,"티셔츠", "면100%", 10000, 0),
                createItem(TOP,"패딩", "거위털100%", 100000, 0),
                createItem(BOTTOM,"청바지", "안찢어짐", 15000, 0)
        );

        itemRepository.saveAll(itemList);
        final Member savedMember = memberRepository.save(createMember());

        final Stock stock1 = Stock.create("티셔츠", 2);
        final Stock stock2 = Stock.create("패딩", 2);
        stockRepository.saveAll(List.of(stock1, stock2));

        final List<String> itemName = List.of("티셔츠", "패딩");
        OrderRequest orderRequest = new OrderRequest(itemName, savedMember.getId());

        // when
        OrderResponse order = orderService.createOrder(orderRequest);

        // then
        assertThat(order.getItems()).hasSize(2)
                .extracting("name", "price")
                .containsExactlyInAnyOrder(
                        tuple("티셔츠", 10000),
                        tuple("패딩", 100000)
                );
    }

    @Test
    void createOrderLackStock() {
        // given
        List<Item> itemList = List.of(
                createItem(TOP,"티셔츠", "면100%", 10000, 0),
                createItem(TOP,"패딩", "거위털100%", 100000, 0),
                createItem(BOTTOM,"청바지", "안찢어짐", 15000, 0)
        );

        itemRepository.saveAll(itemList);
        final Member savedMember = memberRepository.save(createMember());

        final Stock stock1 = Stock.create("티셔츠", 1);
        final Stock stock2 = Stock.create("패딩", 1);
        stockRepository.saveAll(List.of(stock1, stock2));

        final List<String> itemName = List.of("티셔츠", "티셔츠", "패딩");
        OrderRequest orderRequest = new OrderRequest(itemName, savedMember.getId());

        // expected
        assertThatThrownBy(() -> orderService.createOrder(orderRequest))
                .isInstanceOf(NotEnoughStockException.class)
                .hasMessage("재고가 부족합니다.");

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

    private Item createItem(ItemType type, String name, String itemDescription, int price, int sale) {

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