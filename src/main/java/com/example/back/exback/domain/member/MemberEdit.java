package com.example.back.exback.domain.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEdit {

    private String userId;
    private String userPw;
    private Integer age;
    private String phone;
    private char gender;
    private String zipcode;
    private String address;
    private String addressDetail;

    @Builder
    public MemberEdit(String userId, String userPw, Integer age, String phone, char gender, String zipcode, String address, String addressDetail) {
        this.userId = userId;
        this.userPw = userPw;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.zipcode = zipcode;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
