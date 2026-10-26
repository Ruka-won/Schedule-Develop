package com.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {
    //속성
    private Long userId;
    private String title;
    private String contents;

    //생성자
}
