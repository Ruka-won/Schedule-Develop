package com.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class ScheduleDeleteResponseDto {
    //속성
    private final String message;

    //생성자
    public ScheduleDeleteResponseDto(String message) {
        this.message = message;
    }

    //기능
}
