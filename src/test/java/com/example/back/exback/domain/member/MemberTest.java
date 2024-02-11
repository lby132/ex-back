package com.example.back.exback.domain.member;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    @Test
    void createMember() {
        // given
        JoinRequest request = joinRequest();

        //when
        Member newMember = Member.createMember(request);

        // then
        assertEquals(newMember.getUserId(), "memberA");
        assertEquals(newMember.getUserPw(), "1234");
        assertEquals(newMember.getPhone(), "010-9990-1123");
        assertEquals(newMember.getGender(), 'M');
        assertEquals(newMember.getAddress().getZipcode(), "06222");
    }

    @DisplayName("초기 가입자는 회원탈퇴(singOut), 정지여부(banned)가 N 이어야 한다.")
    @Test
    void memberStatus() {
        // given
        JoinRequest request = joinRequest();

        Member newMember = Member.createMember(request);

        // then
        assertEquals(newMember.getSingOut(), 'N');
        assertEquals(newMember.getBanned(), 'N');
    }

    private JoinRequest joinRequest() {
        return JoinRequest.builder()
                .zipcode("06222")
                .address("서울시")
                .addressDetail("27-5번지")
                .userId("memberA")
                .userPw("1234")
                .age(24)
                .phone("010-9990-1123")
                .gender('M')
                .build();
    }

}