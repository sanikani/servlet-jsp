package com.nhnacademy.student.config;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
@HandlesTypes(value = com.nhnacademy.student.controller.Command.class)
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        log.debug("WebAppInitializer initialized!");
        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(set);
        servletContext.setAttribute("controllerFactory", controllerFactory);
    }
}
