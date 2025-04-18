package com.nhnacademy.stopwatch;

import java.lang.reflect.Method;

public class ListTestProxy implements PerformanceTestable {
    private final PerformanceTestable performanceTestable;

    public ListTestProxy(PerformanceTestable performanceTestable) {
        this.performanceTestable = performanceTestable;
    }

    @Override
    public void test() {
        long start = System.currentTimeMillis();
        System.out.println("##시간측정 시작: " + start);
        performanceTestable.test();
        long end = System.currentTimeMillis();
        System.out.println("##시간측정 종료" + end);
        long result = (end - start) / 1000;
        System.out.println("실행시간(초):" + result);
    }
    
    private boolean hasStopWatch() {
        for (Method method : performanceTestable.getClass().getDeclaredMethods()) {
            if (method.getAnnotation(StopWatch.class) != null) {
                return true;
            }
        }
        return false;
    }
}
