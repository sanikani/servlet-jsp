package com.nhnacademy.student.controller;

import com.nhnacademy.student.config.ControllerFactory;
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

//        try {
////            Command command = resolveServlet(req.getServletPath(), req.getMethod());
//            ControllerFactory controllerFactory = (ControllerFactory) req.getServletContext().getAttribute("controllerFactory");
//            Command command = (Command) controllerFactory.getBean(req.getMethod(), req.getServletPath());
//            log.debug("command={}", command);
//            if (command == null) {
//                throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "요청한 경로를 찾을 수 없습니다");
//            }
//
//            String view = command.execute(req, resp);
//            if (view.startsWith(REDIRECT_PREFIX)) {
//                log.debug("redirect view={}",view.substring(REDIRECT_PREFIX.length()));
////                command = resolveServlet(view.substring(REDIRECT_PREFIX.length()), "GET");
//                log.debug("method={}", req.getMethod());
//                command = (Command) controllerFactory.getBean(req.getMethod(), view.substring(REDIRECT_PREFIX.length()));
//                if (command == null) {
//                    throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "요청한 경로를 찾을 수 없습니다");
//                }
//                view = command.execute(req, resp);
//                req.getRequestDispatcher(view).include(req, resp);
//            } else {
//                log.debug("view={}",view);
//                req.getRequestDispatcher(view).include(req, resp);
//            }
//
//        }
        try {
            ControllerFactory controllerFactory = (ControllerFactory) req.getServletContext().getAttribute("controllerFactory");
            Command command = (Command) controllerFactory.getBean(req.getMethod(), req.getServletPath());
            log.debug("command={}", command.getClass().getName());

            if (command == null) {
                throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "요청한 경로를 찾을 수 없습니다");
            }

            String view = command.execute(req, resp);

            if (view.startsWith(REDIRECT_PREFIX)) {
                // redirect:/xxx.do -> xxx.do 로 변환
                String redirectPath = view.substring(REDIRECT_PREFIX.length());
                log.debug("Redirecting to: {}", redirectPath);

                // 클라이언트에게 리다이렉트 명령
                resp.sendRedirect(redirectPath);
            } else {
                log.debug("Forwarding to view: {}", view);
                req.getRequestDispatcher(view).include(req, resp);
            }

        }
        catch (Exception e) {

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

}
