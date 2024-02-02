package com.example.back.exback.domain.noticeboard;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardCommon {

    private String writer;
    private String content;
}
