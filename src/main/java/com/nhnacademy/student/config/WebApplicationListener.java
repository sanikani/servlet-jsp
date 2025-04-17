package com.nhnacademy.student.config;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@WebListener
@Slf4j
public class WebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("WebApplicationListener initialized!");
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        for (int i = 1; i <= 10; i++) {
            Gender gender;
            int age = (int) (Math.random() * 10) + 20;
            if (i % 2 == 0) {
                gender = Gender.M;
            }else {
                gender = Gender.F;
            }
            Student student = new Student("student" + i, "아카데미" + i, gender, age, LocalDateTime.now());
            studentRepository.save(student);
        }
        context.setAttribute("studentRepository", studentRepository);

    }
}
