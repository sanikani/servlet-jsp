package com.nhnacademy.student.domain;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Student {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDateTime createdAt;

    public Student(String id, String name, Gender gender, int age, LocalDateTime createdAt) {
        if (id == null || name == null) {
            throw new IllegalArgumentException("id와 이름을 입력해 주세요");
        }
        if (age < 0) {
            throw new IllegalArgumentException("올바른 나이를 입력해 주세요");
        }
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = createdAt;
    }
}
