package com.example.back.exback.api.service.noticeboard;

import com.example.back.exback.api.controller.noticeboard.request.BoardRequest;
import com.example.back.exback.api.controller.noticeboard.response.BoardResponse;
import com.example.back.exback.api.exception.PostNotFound;
import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import com.example.back.exback.domain.noticeboard.board.Board;
import com.example.back.exback.domain.noticeboard.board.BoardEditor;
import com.example.back.exback.domain.noticeboard.board.BoardRepository;
import com.example.back.exback.domain.noticeboard.board.BoardStatus;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    EntityManager em;

    @Test
    void boardList() {
        // given
        Address address = new Address("06222", "서울시", "27-5번지");
        Member newMember = Member.createMember(address, "memberA", "1234", "010-9990-1123", 'M');
        BoardCommon boardCommon = new BoardCommon("memberA", "게시판테스트");

        Address address2 = new Address("06222", "서울시", "27-5번지");
        Member newMember2 = Member.createMember(address2, "memberB", "1234", "010-9990-1123", 'M');
        BoardCommon boardCommon2 = new BoardCommon("memberB", "게시판테스트");
        em.persist(newMember);
        em.persist(newMember2);

        Board newBoard1 = Board.registBoard(newMember, "board1", boardCommon);
        Board newBoard2 = Board.registBoard(newMember, "board2", boardCommon2);
        em.persist(newBoard1);
        em.persist(newBoard2);

        em.flush();
        em.clear();
        // when
        List<BoardResponse> boardList = boardService.getBoardAll();

        // then
        assertThat(boardList.size()).isEqualTo(2);
        assertThat(boardList.get(0).getWriter()).isEqualTo("memberA");
        assertThat(boardList.get(1).getWriter()).isEqualTo("memberB");
    }

    @Test
    void boardSave() {
        // given
        Long saveBoardId = createBoard();

        // when
        List<BoardResponse> boardList = boardService.getBoardAll();

        // then
        assertThat(saveBoardId).isNotNull();
        assertThat(boardList.size()).isEqualTo(1);
        assertThat(boardList.get(0).getTitle()).isEqualTo("제목1");
        assertThat(boardList.get(0).getContent()).isEqualTo("내용");
    }

    @Test
    void boardSaveException() {
        // given
        BoardRequest request = new BoardRequest("제목1", "내용", "memberA");

        // expected
        assertThatThrownBy(() -> boardService.registrationBoard(1L, request))
                .isInstanceOf(PostNotFound.class);

    }

    @Test
    void boardEdit() {
        // given
        Long saveBoardId = createBoard();

        // when
        final BoardEditor editor = new BoardEditor("제목2", "memberA", "수정내용");
        boardService.edit(saveBoardId, editor);

        // then
        Board board = boardRepository.findById(saveBoardId).orElseThrow();

        assertThat(board.getTitle()).isEqualTo("제목2");
        assertThat(board.getBoardCommon().getContent()).isEqualTo("수정내용");
        assertThat(board.getBoardStatus()).isEqualTo(BoardStatus.UPDATE);
    }

    @Test
    void boardDelete() {
        // given
        Long saveBoardId = createBoard();

        // when
        boardService.delete(saveBoardId);

        // then
        Board board = boardRepository.findById(saveBoardId).orElseThrow();

        assertThat(board.getDeleteYn()).isEqualTo('Y');
    }


    private Long createBoard() {
        Address address = new Address("06222", "서울시", "27-5번지");
        Member newMember = Member.createMember(address, "memberA", "1234", "010-9990-1123", 'M');
        em.persist(newMember);

        BoardRequest request = new BoardRequest("제목1", "내용", "memberA");

        return boardService.registrationBoard(newMember.getId(), request);
    }
}