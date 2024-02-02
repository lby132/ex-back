package com.example.back.exback.domain.address;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {

    private String zipcode;
    private String address;
    private String addressDetail;

}
