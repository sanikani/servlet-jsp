package com.nhnacademy.stopwatch;

public class ListTestMain {
    public static void main(String[] args) {
        ListTestProxy listTestProxy = new ListTestProxy(new ArrayListTest());
        listTestProxy.test();
        ListTestProxy linkedListTestProxy = new ListTestProxy(new LinkedListTest());
        linkedListTestProxy.test();
    }
}
