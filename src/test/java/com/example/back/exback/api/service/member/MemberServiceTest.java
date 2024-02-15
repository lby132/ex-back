package com.example.back.exback.api.service.member;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.api.exception.InvalidRequest;
import com.example.back.exback.api.exception.PostNotFound;
import com.example.back.exback.domain.address.Address;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberEdit;
import com.example.back.exback.domain.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.tuple;

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

    @Test
    void findMembers() {
        // given
        Member memberA = Member.builder()
                .address(new Address("0444", "서울시", "27-3번지"))
                .userId("memberA")
                .userPw("1234")
                .age(24)
                .phone("01010102320")
                .gender('M')
                .regDate(LocalDateTime.now())
                .build();

        Member memberB = Member.builder()
                .address(new Address("0444", "서울시", "27-1번지"))
                .userId("memberB")
                .userPw("1234")
                .age(24)
                .phone("01010102320")
                .gender('W')
                .regDate(LocalDateTime.now())
                .build();

        memberRepository.saveAll(List.of(memberA, memberB));

        // when
        List<Member> member = memberService.findMembers();

        // then
        assertThat(member).hasSize(2)
                .extracting("userId", "age", "gender")
                .containsExactlyInAnyOrder(
                        tuple("memberA", 24, 'M'),
                        tuple("memberB", 24, 'W')
                );
    }

    @Test
    @DisplayName("회원 한명만 조회한다.")
    void findOneMember() {
        // given
        Member member = Member.builder()
                .address(new Address("0444", "서울시", "27-3번지"))
                .userId("memberA")
                .userPw("1234")
                .age(24)
                .phone("01010102320")
                .gender('M')
                .regDate(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        // when
        Member findOne = memberService.findOneMember(member.getUserId());

        // then
        assertThat(findOne)
                .extracting("userId", "age", "gender")
                .containsExactly("memberA", 24, 'M');
    }

    @Test
    void memberEdit() {
        // given
        Member member = Member.builder()
                .address(new Address("0444", "서울시", "27-3번지"))
                .userId("memberA")
                .userPw("1234")
                .age(24)
                .phone("01010102320")
                .gender('M')
                .regDate(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        // when
        MemberEdit editMember = MemberEdit.builder()
                .zipcode("06222")
                .address("서울시")
                .addressDetail("27-5번지")
                .userId("memberB")
                .userPw("1234")
                .age(24)
                .phone("01022223333")
                .gender('M')
                .build();

        memberService.edit(member.getId(), editMember);

        // then
        assertThat(member.getUserId()).isEqualTo("memberB");
        assertThat(member.getPhone()).isEqualTo("01022223333");
    }

}