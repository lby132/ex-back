package com.example.back.exback.api.controller.member;

import com.example.back.exback.api.ApiResponse;
import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.api.controller.member.response.MemberResponse;
import com.example.back.exback.api.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v1/members")
    public ApiResponse<List<MemberResponse>> findMembers() {
        final List<MemberResponse> memberList = memberService.findMembers().stream()
                .map(m -> new MemberResponse(m.getUserId()))
                .collect(Collectors.toList());
        return ApiResponse.ok(memberList);
    }

    @PostMapping("/v1")
    public void joinMember(@RequestBody @Valid JoinRequest request) {
        memberService.join(request);
    }
}
