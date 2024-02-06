package com.example.back.exback.api.controller.noticeboard.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequest {

    @NotEmpty(message = "제목 입력은 필수입니다.")
    private String title;
    private String content;
    private String writer;

}
