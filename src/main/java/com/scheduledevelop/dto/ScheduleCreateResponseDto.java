package com.scheduledevelop.dto;

import java.time.LocalDateTime;

public class ScheduleCreateResponseDto {
    //속성
    private final Long id;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;


    //생성자
    public ScheduleCreateResponseDto(Long id, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    //기능
    public Long getId() {
        return this.id;
    }

    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    public LocalDateTime getUpdateAt() {
        return this.updateAt;
    }

}
