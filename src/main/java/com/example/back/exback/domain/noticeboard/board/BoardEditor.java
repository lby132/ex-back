package com.example.back.exback.domain.noticeboard.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEditor {

    private String title;
    private String writer;
    private String content;
}
