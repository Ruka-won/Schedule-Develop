package com.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.YesNoConverter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
@SoftDelete(columnName = "is_deleted",
        converter = YesNoConverter.class)
public class User {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String name;

    @Column(length = 100,nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime updateAt;

    //생성자

    //JPA 에서 사용하는 기본 생성자
    protected User() {

    }
    // 내가 직접 사용하는 생성자
    public User(String name, String email) {
        this.name = name;
        this.email = email;
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
    public User update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }
}
