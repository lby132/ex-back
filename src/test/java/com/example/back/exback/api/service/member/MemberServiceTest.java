package com.example.back.exback.api.service.member;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.api.exception.InvalidRequest;
import com.example.back.exback.api.exception.PostNotFound;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void joinTest() {
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

        // when
        Long joinMemberId = memberService.join(request);

        // then
        Member findMember = memberRepository.findById(joinMemberId).orElseThrow(PostNotFound::new);

        assertThat(findMember.getUserId()).isEqualTo("memberA");
        assertThat(findMember.getAge()).isEqualTo(24);
        assertThat(findMember.getAddress().getZipcode()).isEqualTo("06222");
        assertThat(findMember.getAddress().getAddressDetail()).isEqualTo("27-5번지");
        assertThat(findMember.getAddress().getAddress()).isEqualTo("서울시");
        assertThat(findMember.getPhone()).isEqualTo("010-9990-1123");
        assertThat(findMember.getGender()).isEqualTo('M');
    }

    @Test
    @DisplayName("같은 이름으로 가입할 경우 에러를 발생한다.")
    void joinTest2() {
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

        // expected
        memberService.join(request);
        assertThatThrownBy(() -> memberService.join(request))
                .isInstanceOf(InvalidRequest.class).hasMessage("이미 존재하는 아이디입니다.");
    }

}