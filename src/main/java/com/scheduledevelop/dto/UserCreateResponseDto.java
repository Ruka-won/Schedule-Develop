package com.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class UserCreateResponseDto {
    //속성
    private final Long id;


    //생성자
    public UserCreateResponseDto(Long id) {
        this.id = id;
    }

    //기능
}
