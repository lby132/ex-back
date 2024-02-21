package com.example.back.exback.api.controller.item;

import com.example.back.exback.api.controller.item.request.ItemRequest;
import com.example.back.exback.domain.item.ItemSellingStatus;
import com.example.back.exback.domain.item.ItemType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ItemControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createItem() throws Exception {
        // given
        ItemRequest item = ItemRequest.builder()
                .type(ItemType.TOP)
                .name("티셔츠")
                .price(10000)
                .sellingStatus(ItemSellingStatus.SELLING)
                .build();

        // expected
        mockMvc.perform(
                        post("/api/v1/items/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(item)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));

    }

    @Test
    void createItemRequiredType() throws Exception {
        // given
        ItemRequest item = ItemRequest.builder()
                .name("티셔츠")
                .price(10000)
                .sellingStatus(ItemSellingStatus.SELLING)
                .build();

        // expected
        mockMvc.perform(
                        post("/api/v1/items/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(item)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}