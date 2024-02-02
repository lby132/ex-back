package com.example.back.exback.api.controller.noticeboard.response;

import com.example.back.exback.domain.noticeboard.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardResponse {

    private String title;
    private String writer;
    private String content;
    private LocalDateTime regDate;

    @Builder
    public BoardResponse(String title, String writer, String content, LocalDateTime regDate) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.regDate = regDate;
    }

    public static BoardResponse of(Board board) {
        return BoardResponse.builder()
                .title(board.getTitle())
                .writer(board.getBoardCommon().getWriter())
                .content(board.getBoardCommon().getContent())
                .regDate(board.getRegDate())
                .build();
    }
}
