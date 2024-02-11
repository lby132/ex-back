package com.example.back.exback.api.controller.member.requset;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequest {

    private String zipcode;
    private String address;
    private String addressDetail;
    private String userId;
    private String userPw;
    private int age;
    private String phone;
    private char gender;

    @Builder
    public JoinRequest(String zipcode, String address, String addressDetail, String userId, String userPw, int age, String phone, char gender) {
        this.zipcode = zipcode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.userId = userId;
        this.userPw = userPw;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
    }
}
