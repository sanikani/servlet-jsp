package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "studentRegisterServlet", value = "/student/register")
@Slf4j
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init() {
        studentRepository = (StudentRepository) getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("action","/student/register");
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
            log.error("Student 필드 오류", e);
            res.sendError(HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
            return;
        }

        res.sendRedirect("/student/view?id=" + id);
    }
}
