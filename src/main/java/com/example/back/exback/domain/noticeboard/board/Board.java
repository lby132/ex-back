package com.example.back.exback.domain.noticeboard.board;

import com.example.back.exback.domain.BaseEntity;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.example.back.exback.domain.noticeboard.board.BoardStatus.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Embedded
    private BoardCommon boardCommon;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    @Column(columnDefinition = "char default 'N'")
    private char deleteYn;

    @Builder
    public Board(Member member, String title, BoardCommon boardCommon, BoardStatus boardStatus, LocalDateTime regDate) {
        this.member = member;
        this.title = title;
        this.boardCommon = boardCommon;
        this.boardStatus = boardStatus;
        this.regDate = regDate;
    }


    public static Board registBoard(Member member, String title, BoardCommon boardCommon) {
        return Board.builder()
                .member(member)
                .title(title)
                .boardCommon(boardCommon)
                .boardStatus(NEW)
                .regDate(LocalDateTime.now())
                .build();
    }

    public void updateBoard(BoardEditor boardEditor) {
        this.title = boardEditor.getTitle();
        this.boardCommon = new BoardCommon(boardEditor.getWriter(), boardEditor.getContent());
        this.boardStatus = UPDATE;
        this.updateDate = LocalDateTime.now();
    }

    public void deleteBoard() {
        deleteYn = 'Y';
    }
}
