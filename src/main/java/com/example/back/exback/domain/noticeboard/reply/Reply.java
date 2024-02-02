package com.example.back.exback.domain.noticeboard.reply;

import com.example.back.exback.domain.BaseEntity;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import com.example.back.exback.domain.noticeboard.board.Board;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Embedded
    private BoardCommon boardCommon;

    private LocalDateTime regDate;

    @Builder
    public Reply(Board board, BoardCommon boardCommon, LocalDateTime regDate) {
        this.board = board;
        this.boardCommon = boardCommon;
        this.regDate = regDate;
    }

    public static Reply createPlay(Board board, BoardCommon boardCommon) {
        return Reply.builder()
                .board(board)
                .boardCommon(boardCommon)
                .regDate(LocalDateTime.now())
                .build();
    }
}
