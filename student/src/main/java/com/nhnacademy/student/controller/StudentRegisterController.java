package com.nhnacademy.student.controller;

import com.nhnacademy.student.config.RequestMapping;
import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.ApiException;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.POST)
public class StudentRegisterController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String genderParam = req.getParameter("gender");
        String ageParam = req.getParameter("age");
        Gender gender = genderParam.equals("M") ? Gender.M : Gender.F;
        int age = Integer.parseInt(ageParam);
        Student student;

        try {
            student = new Student(id, name, gender, age, LocalDateTime.now());
            studentRepository.save(student);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "잘못된 입력입니다");
        }

        return "redirect:/student/view.do?id=" + id;
    }
}
