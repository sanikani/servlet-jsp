package com.nhnacademy.student.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", value = "*.do")
public class FrontServlet extends HttpServlet {

    public static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            String servletPath = resolveServlet(req.getServletPath(), req.getServletContext());
            if (servletPath == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "요청한 경로를 찾을 수 없습니다");
                return;
            }

            req.getRequestDispatcher(servletPath).include(req, resp);

            String view = (String) req.getAttribute("view");
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.debug("redirect view={}",view.substring(REDIRECT_PREFIX.length()));
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                log.debug("view={}",view);
                req.getRequestDispatcher(view).include(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("exceptionType", e.getClass());
            req.setAttribute("message", e.getMessage());
            req.setAttribute("exception", e);
            req.setAttribute("requestURI", req.getRequestURI());
            req.getRequestDispatcher("/error").forward(req, resp);
        }
    }

    private String resolveServlet(String servletPath, ServletContext context) {
        // 요청이 .do로 끝나면 .do를 제거한 경로를 반환
        if (servletPath.endsWith(".do")) {
            String path = servletPath.substring(0, servletPath.length() - 3);
            if (context.getRequestDispatcher(path) != null) {
                return path;
            }
        }
        return null;
    }
}
