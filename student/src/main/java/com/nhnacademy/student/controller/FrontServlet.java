package com.nhnacademy.student.controller;

import com.nhnacademy.student.exception.ApiException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static jakarta.servlet.RequestDispatcher.*;

@Slf4j
@WebServlet(name = "frontServlet", value = "*.do")
public class FrontServlet extends HttpServlet {

    public static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            Command command = resolveServlet(req.getServletPath(), req.getMethod());
            if (command == null) {
                throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "요청한 경로를 찾을 수 없습니다");
            }

            String view = command.execute(req, resp);
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.debug("redirect view={}",view.substring(REDIRECT_PREFIX.length()));
                command = resolveServlet(view.substring(REDIRECT_PREFIX.length()), "GET");
                if (command == null) {
                    throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "요청한 경로를 찾을 수 없습니다");
                }
                view = command.execute(req, resp);
                req.getRequestDispatcher(view).include(req, resp);
            } else {
                log.debug("view={}",view);
                req.getRequestDispatcher(view).include(req, resp);
            }

        } catch (Exception e) {

            int statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            if (e instanceof ApiException) {
                statusCode = ((ApiException) e).getStatusCode();
            }

            req.setAttribute("statusCode", statusCode);
            req.setAttribute("exceptionType", e.getClass());
            req.setAttribute("message", e.getMessage());
            req.setAttribute("exception", e);
            req.setAttribute("requestURI", req.getRequestURI());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req,resp);
        }
    }

    private Command resolveServlet(String servletPath, String method) {
        if (servletPath.equals("/student/list.do") && method.equals("GET")) {
            return new StudentController();
        } else if (servletPath.equals("/student/view.do") && method.equals("GET")) {
            return new StudentViewController();
        } else if (servletPath.equals("/student/register.do") && method.equals("GET")) {
            return new StudentRegisterFormController();
        } else if (servletPath.equals("/student/register.do") && method.equals("POST")) {
            return new StudentRegisterController();
        } else if (servletPath.equals("/student/update.do") && method.equals("GET")) {
            return new StudentUpdateFormController();
        } else if (servletPath.equals("/student/update.do") && method.equals("POST")) {
            return new StudentUpdateController();
        } else if (servletPath.equals("/student/delete.do") && method.equals("POST")) {
            return new StudentDeleteController();
        }

        return null;
    }

}
