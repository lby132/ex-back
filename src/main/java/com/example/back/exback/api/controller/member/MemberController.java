package com.example.back.exback.api.controller.member;

import com.example.back.exback.api.ApiResponse;
import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.api.controller.member.response.MemberResponse;
import com.example.back.exback.api.service.member.MemberService;
import com.example.back.exback.domain.member.Member;
import com.example.back.exback.domain.member.MemberEdit;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v1/members")
    public ApiResponse<List<MemberResponse>> findMembers() {
        List<MemberResponse> memberList = memberService.findMembers().stream()
                .map(m -> new MemberResponse(m.getUserId()))
                .collect(Collectors.toList());
        return ApiResponse.ok(memberList);
    }

    @GetMapping("/v1/members/{userId}")
    public ApiResponse<MemberResponse> findOneMember(@PathVariable("userId") String userId) {
        Member oneMember = memberService.findOneMember(userId);
        return ApiResponse.ok(new MemberResponse(oneMember.getUserId()));
    }

    @PostMapping("/v1/members/join")
    public void joinMember(@RequestBody @Valid JoinRequest request) {
        memberService.join(request);
    }

    @PostMapping("/v1/members/edit/{memberId}")
    public void editMember(@PathVariable("memberId") Long memberId, @RequestBody MemberEdit memberEdit) {
        memberService.edit(memberId, memberEdit);
    }

}
