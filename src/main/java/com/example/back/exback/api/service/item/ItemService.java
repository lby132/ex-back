package com.example.back.exback.api.service.item;

import com.example.back.exback.api.controller.item.request.ItemRequest;
import com.example.back.exback.api.controller.item.response.ItemResponse;
import com.example.back.exback.domain.item.Item;
import com.example.back.exback.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponse createItem(ItemRequest request) {
        Item item = request.toEntity();
        Item saveItem = itemRepository.save(item);
        return ItemResponse.of(saveItem);
    }
}
