package com.nhnacademy.stopwatch;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest implements PerformanceTestable{

    @StopWatch
    @Override
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            list.add(i);
        }
    }
}
