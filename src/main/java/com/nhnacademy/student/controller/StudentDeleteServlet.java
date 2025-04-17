package com.nhnacademy.student.controller;

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
@WebServlet(name = "studentDeleteServlet", value = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        studentRepository = (StudentRepository) servletConfig.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (!studentRepository.existById(id)) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다");
        }

        studentRepository.deleteById(id);

        resp.sendRedirect("/student/list");
    }
}
