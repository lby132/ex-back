package com.example.back.exback.api.controller.noticeboard.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequest {

    private String title;
    private String content;
    private String writer;
}
