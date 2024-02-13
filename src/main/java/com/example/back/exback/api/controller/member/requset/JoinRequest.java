package com.example.back.exback.api.controller.member.requset;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequest {

    @NotEmpty(message = "아이디 입력은 필수 입니다.")
    private String userId;

    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    private String userPw;

    @NotEmpty(message = "나이 입력은 필수 입니다.")
    private int age;

    private String zipcode;
    private String address;
    private String addressDetail;
    private String phone;
    private char gender;

    @Builder
    public JoinRequest(String userId, String userPw, int age, String zipcode, String address, String addressDetail, String phone, char gender) {
        this.userId = userId;
        this.userPw = userPw;
        this.age = age;
        this.zipcode = zipcode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.phone = phone;
        this.gender = gender;
    }
}
