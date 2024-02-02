package com.example.back.exback.domain.noticeboard.board;

import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void regBoard() {
        // given
        Address address = new Address("06222", "서울시", "27-5번지");
        Member newMember = Member.createMember(address, "memberA", "1234", "010-9990-1123", 'M');
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