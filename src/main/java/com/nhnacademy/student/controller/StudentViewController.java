package com.nhnacademy.student.controller;

import ch.qos.logback.core.util.StringUtil;
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

@Slf4j
public class StudentViewController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        if (!studentRepository.existById(id)) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "존재하지 않는 회원입니다");
        }
        Student student = studentRepository.getStudentById(id);
        req.setAttribute("student", student);
        if (StringUtil.isNullOrEmpty(id)) {
            throw new ApiException(HttpServletResponse.SC_BAD_REQUEST, "존재하지 않는 회원입니다");
        }

        return "/WEB-INF/view.jsp";
    }
}
