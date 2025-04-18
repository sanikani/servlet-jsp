package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.config.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/student/delete.do", method = RequestMapping.Method.POST)
public class StudentDeleteController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        log.debug("id:{}",id);
        if (!studentRepository.existById(id)) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다");
        }
        studentRepository.deleteById(id);
        //view를 return 합니다.

        return "redirect:/student/list.do";
    }
}
