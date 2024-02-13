package com.example.back.exback.domain.member;

import com.example.back.exback.api.controller.member.requset.JoinRequest;
import com.example.back.exback.domain.BaseEntity;
import com.example.back.exback.domain.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private Address address;

    private String userId;
    private String userPw;
    private int age;
    private String phone;
    private char gender;
    private LocalDateTime regDate;
    //정지여부
    @Column(columnDefinition = "char default 'N'")
    private char banned = 'N';
    //회원탈퇴
    @Column(columnDefinition = "char default 'N'")
    private char singOut = 'N';

    @Builder
    public Member(Address address, String userId, String userPw, int age, String phone, char gender, LocalDateTime regDate) {
        this.address = address;
        this.userId = userId;
        this.userPw = userPw;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.regDate = regDate;
    }

    public static Member createMember(JoinRequest request) {
        final Address address = new Address(request.getZipcode(), request.getAddress(), request.getAddressDetail());
        return Member.builder()
                .address(address)
                .userId(request.getUserId())
                .userPw(request.getUserPw())
                .age(request.getAge())
                .phone(request.getPhone())
                .gender(request.getGender())
                .regDate(LocalDateTime.now())
                .build();
    }
}
