//package com.nhnacademy.student.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
//@WebFilter("/*")
//@Slf4j
//public class CharacterEncodingFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) {}
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        log.info("CharacterEncodingFilter.doFilter");
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {}
//}

