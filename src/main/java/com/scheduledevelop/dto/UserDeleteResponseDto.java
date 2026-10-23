package com.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class UserDeleteResponseDto {
    //속성
    private final String message;


    //생성자
    public UserDeleteResponseDto(String message) {
        this.message = message;
    }

    //기능
}
