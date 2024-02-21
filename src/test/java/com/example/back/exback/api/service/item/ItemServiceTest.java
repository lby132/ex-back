package com.example.back.exback.api.service.item;

import com.example.back.exback.api.controller.item.request.ItemRequest;
import com.example.back.exback.api.controller.item.response.ItemResponse;
import com.example.back.exback.domain.item.ItemRepository;
import com.example.back.exback.domain.item.ItemSellingStatus;
import com.example.back.exback.domain.item.ItemType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.example.back.exback.domain.item.ItemSellingStatus.*;
import static com.example.back.exback.domain.item.ItemType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void createItem() {
        // given
        ItemRequest item = ItemRequest.builder()
                .type(TOP)
                .sellingStatus(SELLING)
                .price(10000)
                .name("티셔츠")
                .sale(0)
                .build();

        // when
        ItemResponse itemResponse = itemService.createItem(item);

        // then
        assertThat(itemResponse)
                .extracting("type", "price", "name", "sellingStatus")
                .containsExactlyInAnyOrder(TOP, 10000, "티셔츠", SELLING);
    }

    @Test
    @DisplayName("sale을 지정하지 않았을땐 기본값이 0 이어야 한다.")
    void createItemDefaultSale() {
        ItemRequest item = ItemRequest.builder()
                .type(TOP)
                .sellingStatus(SELLING)
                .price(10000)
                .name("티셔츠")
                .sale(0)
                .build();

        // when
        ItemResponse itemResponse = itemService.createItem(item);

        // then
        assertThat(itemResponse).isNotNull();
        assertThat(itemResponse.getSale()).isEqualTo(0);
    }
}