package com.example.back.exback.domain.noticeboard.board;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void regBoard() {
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

        // when
        Board newBoard = Board.registBoard(newMember, "board1", boardCommon);

        // then
        assertThat(newBoard.getMember()).isEqualTo(newMember);
        assertThat(newBoard.getTitle()).isEqualTo("board1");
        assertThat(newBoard.getBoardCommon().getWriter()).isEqualTo("memberA");
        assertThat(newBoard.getBoardCommon().getContent()).isEqualTo("게시판테스트");
    }

}