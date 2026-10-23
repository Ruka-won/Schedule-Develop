package com.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    //속성
    private String name;
    private String email;

    //생성자
    public UserUpdateRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //기능
}
