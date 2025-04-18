package com.nhnacademy.student.controller;

import com.nhnacademy.student.config.RequestMapping;
import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.ApiException;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.GET)
public class StudentUpdateFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        //todo 학생조회
        if (!studentRepository.existById(req.getParameter("id"))) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "존재하지 않는 회원입니다");
        }

        req.setAttribute("student", studentRepository.getStudentById(req.getParameter("id")));
        req.setAttribute("action", "/student/update.do");

        return "/WEB-INF/register.jsp";
    }
}