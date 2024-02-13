package com.example.back.exback.api.controller.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MembersResponse<T> {

    private T data;
}
