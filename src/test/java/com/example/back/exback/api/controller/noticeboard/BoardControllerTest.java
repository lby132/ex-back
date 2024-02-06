package com.example.back.exback.api.controller.noticeboard;

import com.example.back.exback.api.controller.noticeboard.request.BoardRequest;
import com.example.back.exback.api.controller.noticeboard.response.BoardResponse;
import com.example.back.exback.api.service.noticeboard.BoardService;
import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import com.example.back.exback.domain.noticeboard.BoardCommon;
import com.example.back.exback.domain.noticeboard.board.Board;
import com.example.back.exback.domain.noticeboard.board.BoardEditor;
import com.example.back.exback.domain.noticeboard.board.BoardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BoardControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Mock
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        boardRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    @Test
    void boardSave() throws Exception {
        // given
        Member newMember = createMember();

        BoardRequest request = BoardRequest.builder()
                .title("제목")
                .content("내용")
                .writer("작성자")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(
                        post("/board/v1/registration/{memberId}", newMember.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertEquals(1, boardRepository.count());

        Board findBoard = boardRepository.findAll().get(0);
        assertEquals("제목", findBoard.getTitle());
        assertEquals("작성자", findBoard.getBoardCommon().getWriter());
        assertEquals("내용", findBoard.getBoardCommon().getContent());
    }

    @Test
    void boardList() throws Exception {
        // given
        List<BoardResponse> result = List.of();

        Mockito.when(boardService.getBoardAll()).thenReturn(result);

        // expected
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/board/v1/list")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data").isArray());

    }

    @Test
    @DisplayName("존재하지 않는 게시글 조희")
    void notFoundBoard() throws Exception {
        // expected
        mockMvc.perform(
                        get("/board/v1/{boardId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void boardEdit() throws Exception {
        // given
        final Member member = createMember();
        final BoardCommon boardCommon = new BoardCommon("memberA", "내용");
        Board board = Board.registBoard(member, "글등록", boardCommon);
        boardRepository.save(board);

        final BoardEditor request = new BoardEditor("글수정", boardCommon.getWriter(), "내용수정");

        final String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(
                        post("/board/v1/edit/{boardId}", board.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findBoardOne() throws Exception {
        // given
        final Member member = createMember();
        final BoardCommon boardCommon = new BoardCommon("memberA", "내용");
        Board board = Board.registBoard(member, "글등록", boardCommon);
        boardRepository.save(board);

        // expected
        mockMvc.perform(
                        get("/board/v1/{boardId}", board.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("제목 입력은 필수입니다.")
    void titleIsRequired() throws Exception {
        // given
        final Member member = createMember();
        final BoardCommon boardCommon = new BoardCommon("memberA", "내용");
        Board board = Board.registBoard(member, null, boardCommon);

        // when
        mockMvc.perform(
                        post("/board/v1/registration/{memberId}", board.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(board)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.data").isEmpty());

        // then
    }

    private Member createMember() {
        Address address = new Address("06222", "서울시", "27-5번지");
        Member newMember = Member.createMember(address, "memberA", "1234", "010-9990-1123", 'M');
        em.persist(newMember);
        return newMember;
    }
}