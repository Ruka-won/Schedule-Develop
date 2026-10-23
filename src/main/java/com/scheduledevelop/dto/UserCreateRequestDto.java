package com.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class UserCreateRequestDto {
    //속성
    private String name;
    private String email;

    //생성자
    public UserCreateRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //기능
}
