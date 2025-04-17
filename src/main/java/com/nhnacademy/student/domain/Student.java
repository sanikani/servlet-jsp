package com.nhnacademy.student.domain;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Student {
    private String id;
    private String name;
    @Setter
    private Gender gender;
    private int age;
    private final LocalDateTime createdAt;

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

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("올바른 나이를 입력해 주세요");
        }
        this.age = age;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }
        this.name = name;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("아이디는 필수입니다.");
        }
        this.id = id;
    }

}
