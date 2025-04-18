package com.nhnacademy.student.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "errorServlet", value = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo exception_type
        req.setAttribute("exceptionType",req.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE));
        //todo message
        req.setAttribute("message",req.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        //todo exception
        req.setAttribute("exception",req.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        //todo request_uri
        req.setAttribute("requestURI", req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));

        //todo /error.jsp forward 처리
        req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
    }
}
