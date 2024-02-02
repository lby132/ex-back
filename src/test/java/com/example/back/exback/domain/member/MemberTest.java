package com.example.back.exback.domain.member;

import com.example.back.exback.domain.address.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    @Test
    void createMember() {
        // given
        Address address = new Address("06222", "서울시", "27-5번지");

        //when
        Member newMember = Member.createMember(address, "memberA", "1234", "010-9990-1123", 'M');

        // then
        assertEquals(newMember.getUserId(), "memberA");
        assertEquals(newMember.getUserPw(), "1234");
        assertEquals(newMember.getPhone(), "010-9990-1123");
        assertEquals(newMember.getGender(), 'M');
        assertEquals(newMember.getAddress(), address);
    }

    @DisplayName("초기 가입자는 회원탈퇴(singOut), 정지여부(banned)가 N 이어야 한다.")
    @Test
    void memberStatus() {
        // given
        Address address = new Address("06222", "서울시", "27-5번지");
        Member newMember = Member.createMember(address, "memberA", "1234", "010-9990-1123", 'M');

        // then
        assertEquals(newMember.getSingOut(), 'N');
        assertEquals(newMember.getBanned(), 'N');
    }

}