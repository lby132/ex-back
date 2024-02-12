package com.example.back.exback.api.service.member;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(JoinRequest request) {
        Member member = memberRepository.findOptionalByUserId(request.getUserId())
                .orElseGet(() -> Member.createMember(request));
        memberRepository.save(member);
        return member.getId();
    }
}
