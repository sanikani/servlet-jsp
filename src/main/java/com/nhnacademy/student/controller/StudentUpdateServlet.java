package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
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
@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 학생조회
        if (!studentRepository.existById(req.getParameter("id"))) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "존재하지 않는 회원입니다");
        }
        req.setAttribute("student", studentRepository.getStudentById(req.getParameter("id")));
        req.setAttribute("action", "/student/update");

        //todo forward : /student/register.jsp
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if (!studentRepository.existById(id)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"존재하지 않는 회원입니다");
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
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }

        //todo /student/view?id=student1 <-- redirect
        resp.sendRedirect("/student/view?id=" + student.getId());
    }
}