package com.example.back.exback.domain.member;

import com.example.back.exback.domain.BaseEntity;
import com.example.back.exback.domain.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public Member(Address address, String userId, String userPw, String phone, char gender, LocalDateTime regDate) {
        this.address = address;
        this.userId = userId;
        this.userPw = userPw;
        this.phone = phone;
        this.gender = gender;
        this.regDate = regDate;
    }

    public static Member createMember(Address address, String userId, String userPw, String phone, char gender) {
        return Member.builder()
                .address(address)
                .userId(userId)
                .userPw(userPw)
                .phone(phone)
                .gender(gender)
                .regDate(LocalDateTime.now())
                .build();
    }
}
