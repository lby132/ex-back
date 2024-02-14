package com.example.back.exback.api.controller.member;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.api.service.member.MemberService;
import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository.deleteAllInBatch();
    }

    @Test
    void joinTest() throws Exception {
        // given
        JoinRequest request = JoinRequest.builder()
                .userId("memberA")
                .userPw("1234")
                .age(24)
                .zipcode("06222")
                .address("서울시")
                .addressDetail("27-5번지")
                .phone("010-9990-1123")
                .gender('M')
                .build();

        // expected
        mockMvc.perform(
                        post("/member/v1/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findMembers() throws Exception {
        // given
        final Member member = Member.builder()
                .address(new Address("0444", "서울시", "27-3번지"))
                .userId("memberA")
                .userPw("1234")
                .age(24)
                .phone("01010102320")
                .gender('M')
                .regDate(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        // expected
        mockMvc.perform(
                        get("/member/v1/members")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.data").isNotEmpty());

    }
}