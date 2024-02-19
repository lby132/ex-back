package com.example.back.exback.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemType {

    TOP("상의"),
    OUTER("외투"),
    BOTTOM("하의"),
    ACCESSORIES("악세사리");

    private final String text;
}
