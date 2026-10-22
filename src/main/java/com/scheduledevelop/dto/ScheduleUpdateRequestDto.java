package com.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    //속성
    private String name;
    private String title;
    private String contents;

    //생성자

    //기능
    public String getName() {
        return this.name;
    }
    public String getTitle() {
        return this.title;
    }
    public String getContents() {
        return this.contents;
    }
}
