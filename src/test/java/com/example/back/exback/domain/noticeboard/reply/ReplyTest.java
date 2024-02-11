package com.example.back.exback.domain.noticeboard.reply;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import com.example.back.exback.domain.noticeboard.board.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReplyTest {

    @Test
    void replyTest() {
        // given
        JoinRequest request = JoinRequest.builder()
                .zipcode("06222")
                .address("서울시")
                .addressDetail("27-5번지")
                .userId("memberA")
                .userPw("1234")
                .age(24)
                .phone("010-9990-1123")
                .gender('M')
                .build();
        Member newMember = Member.createMember(request);

        BoardCommon boardCommon = new BoardCommon("memberA", "게시판테스트");
        Board newBoard = Board.registBoard(newMember, "board1", boardCommon);
        BoardCommon replayCommon = new BoardCommon("replayMemberA", "게시판 댓글 테스트");

        // when
        Reply newPlay = Reply.createPlay(newBoard, replayCommon);

        // then
        assertThat(newPlay.getBoard()).isEqualTo(newBoard);
        assertThat(newPlay.getBoardCommon().getWriter()).isEqualTo("replayMemberA");
        assertThat(newPlay.getBoardCommon().getContent()).isEqualTo("게시판 댓글 테스트");

    }

}