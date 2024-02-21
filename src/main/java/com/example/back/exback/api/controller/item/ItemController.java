package com.example.back.exback.api.controller.item;

import com.example.back.exback.api.ApiResponse;
import com.example.back.exback.api.controller.item.request.ItemRequest;
import com.example.back.exback.api.controller.item.response.ItemResponse;
import com.example.back.exback.api.service.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/v1/items/new")
    public ApiResponse<ItemResponse> createItem(@Valid @RequestBody ItemRequest request) {
        return ApiResponse.ok(itemService.createItem(request));
    }

}
