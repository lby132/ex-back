package com.example.back.exback.api.service.member;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.api.exception.InvalidRequest;
import com.example.back.exback.api.exception.PostNotFound;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberEdit;
import com.example.back.exback.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(JoinRequest request) {
        validateDuplicateId(request);
        Member saveMember = memberRepository.save(Member.createMember(request));
        return saveMember.getId();
    }

    private void validateDuplicateId(JoinRequest request) {
        memberRepository.findOptionalByUserId(request.getUserId())
                .ifPresent(r -> {
                    throw new InvalidRequest("이미 존재하는 아이디입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOneMember(String userId) {
        return memberRepository.findOptionalByUserId(userId)
                .orElseThrow(PostNotFound::new);
    }

    public void edit(Long id, MemberEdit edit) {
        System.out.println("edit = " + edit.getUserId());
        Member member = memberRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        member.editMember(edit);
    }
}
