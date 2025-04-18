package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.ApiException;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class StudentUpdateController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");

        if (!studentRepository.existById(id)) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다");
        }

        Student student = studentRepository.getStudentById(id);

        try {
            studentRepository.update(new Student(
                    id,
                    req.getParameter("name"),
                    Gender.valueOf(req.getParameter("gender")),
                    Integer.parseInt(req.getParameter("age")),
                    LocalDateTime.now())
            );
        } catch (IllegalArgumentException e) {
            log.error("Student 필드 오류", e);
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
        req.setAttribute("id", student.getId());
//        return "redirect:/student/view?id=" + student.getId();
        return "redirect:/student/view.do";
    }
}
