package com.nhnacademy.student.config;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class ControllerFactory  {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c){
        //todo beanMap에 key = method + servletPath, value = Controller instance
        for (Class<?> controller :c){
            RequestMapping annotation = controller.getAnnotation(RequestMapping.class);
            if (Objects.nonNull(annotation)) {
                String method = annotation.method().name();
                String servletPath = annotation.value();
                try {
                    beanMap.put(method + " " + servletPath, controller.getDeclaredConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                log.debug("controller: {}, method: {}, servletPath: {}", controller, method, servletPath);
            }
        }
    }

    public Object getBean(String method, String path){
        //todo beanMap 에서 method+servletPath을 key로 이용하여 Controller instance를 반환합니다.
        return beanMap.get(method + " " + path);
    }
}