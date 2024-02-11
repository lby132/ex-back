package com.example.back.exback.api.service.member;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.api.exception.PostNotFound;
import com.example.back.exback.domain.address.Address;
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

    public void join(JoinRequest request) {
        memberRepository.findByUserId(request.getUserId())
                .orElseGet(() -> Member.createMember(request));
    }
}
