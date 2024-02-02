package com.example.back.exback.api.controller.noticeboard;

import com.example.back.exback.api.controller.noticeboard.request.BoardRequest;
import com.example.back.exback.api.controller.noticeboard.response.BoardResponse;
import com.example.back.exback.api.service.noticeboard.BoardService;
import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import com.example.back.exback.domain.noticeboard.board.Board;
import com.example.back.exback.domain.noticeboard.board.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
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
    @Transactional
    void boardSave() throws Exception {
        // given
        Address address = new Address("06222", "서울시", "27-5번지");
        Member newMember = Member.createMember(address, "memberA", "1234", "010-9990-1123", 'M');
        em.persist(newMember);

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

//        Mockito.when(boardService.getBoardAll()).thenReturn(result);

        // when & then
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

}