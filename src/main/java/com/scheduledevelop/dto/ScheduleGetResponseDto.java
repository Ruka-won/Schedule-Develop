package com.scheduledevelop.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetResponseDto {
    //속성
    private final Long id;
    private final String name;
    private final String title;
    private final String contents;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;


    //생성자
    public ScheduleGetResponseDto(Long id, String name, String title, String contents, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    //기능
}
