package com.scheduledevelop.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleGetAllResponseDto {
    //속성
    private final List<ScheduleGetResponseDto> scheduleList;

    //생성자
    public ScheduleGetAllResponseDto(List<ScheduleGetResponseDto> scheduleList) {
        this.scheduleList = scheduleList;
    }
    //기능

}
