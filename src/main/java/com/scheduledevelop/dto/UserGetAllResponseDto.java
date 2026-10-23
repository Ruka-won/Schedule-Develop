package com.scheduledevelop.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserGetAllResponseDto {

    //속성
    private final List<UserGetDetailResponseDto> userList;

    //생성자
    public UserGetAllResponseDto(List<UserGetDetailResponseDto> userList) {
        this.userList = userList;
    }
    //기능
}
