package com.scheduledevelop.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserGetDetailResponseDto {
    //속성
    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    //생성자
    public UserGetDetailResponseDto(Long id, String name, String email, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    //기능

}
