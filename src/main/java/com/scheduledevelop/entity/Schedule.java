package com.scheduledevelop.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.YesNoConverter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@SoftDelete(columnName = "is_deleted",
        converter = YesNoConverter.class
)

public class Schedule {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime updateAt;


    //JPA 에서 사용하는 기본 생성자
    protected Schedule() {

    }
    // 내가 직접 사용하는 생성자
    public Schedule(String name, String title, String contents) {
        this.name = name;
        this.title = title;
        this.contents = contents;
    }
    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }


    //기능
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }
    public String getContents() {
        return this.contents;
    }

    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    public LocalDateTime getUpdateAt() {
        return this.updateAt;
    }

    //데이터 수정
    public void updateSchedule(String name, String title, String contents) {
        this.name = name;
        this.title = title;
        this.contents = contents;
    }
}
