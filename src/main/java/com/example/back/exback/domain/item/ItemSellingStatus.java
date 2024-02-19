package com.example.back.exback.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ItemSellingStatus {

    SELLING("판매중"),
    HOLD("판매보류"),
    STOP_SELLING("판매중지");

    private final String text;

    public static List<ItemSellingStatus> forDisplay() {
        return List.of(SELLING, HOLD);
    }
}
