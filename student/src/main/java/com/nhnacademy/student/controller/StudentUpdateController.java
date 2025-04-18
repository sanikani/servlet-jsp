package com.nhnacademy.student.controller;

import com.nhnacademy.student.config.RequestMapping;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.ApiException;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.POST)
public class StudentUpdateController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");

        if (!studentRepository.existById(id)) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다");
        }

        try {
            Student student = studentRepository.getStudentById(id);
            student.setName(req.getParameter("name"));
            student.setAge(Integer.parseInt(req.getParameter("age")));
            studentRepository.update(student);
        } catch (IllegalArgumentException e) {
            log.error("Student 필드 오류", e);
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }

        return "redirect:/student/view.do?id=" + id;
    }
}
