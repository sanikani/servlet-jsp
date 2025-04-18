package com.nhnacademy.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionMain {
    public static void main(String[] args) {
        //일반적인 객체 생성
        //User user = new User("marco", 30);
        createUserByReflectionWithNoArgs();
        createUserByReflectionWithArgs();
        callMethodByReflection();
        accessFieldByReflection();
    }

    private static void createUserByReflectionWithNoArgs() {
        try {
            Class<?> userClass = Class.forName(User.class.getName());
            Constructor<?> constructor = userClass.getConstructor();
            User user = (User) constructor.newInstance();
            System.out.println(user);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void createUserByReflectionWithArgs() {
        try {
            Class<?> userClass = Class.forName(User.class.getName());
            Constructor<?> constructor = userClass.getConstructor(String.class, Integer.TYPE);
            User user = (User) constructor.newInstance("marco", 30);
            System.out.println(user);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void callMethodByReflection() {
        try {
            Class<?> clazz = Class.forName(User.class.getName());
            User user = (User) clazz.getDeclaredConstructor().newInstance();
            Method setUserName = clazz.getDeclaredMethod("setUserName",String.class);
            setUserName.invoke(user, "NHN");
            String userName = (String) clazz.getDeclaredMethod("getUserName").invoke(user);
            Method setUserAge = clazz.getDeclaredMethod("setUserAge",Integer.TYPE);
            setUserAge.invoke(user, 30);
            int userAge = (int) clazz.getDeclaredMethod("getUserAge").invoke(user);
            System.out.println("userName = " + userName);
            System.out.println("userAge = " + userAge);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void accessFieldByReflection() {
        try {
            Class<?> clazz = Class.forName(User.class.getName());
            User user = (User) clazz.getDeclaredConstructor().newInstance();
            Field userNameField = clazz.getDeclaredField("userName");
            userNameField.setAccessible(true);
            userNameField.set(user, "marco");
            String userName = (String) userNameField.get(user);
            Field userAgeField = clazz.getDeclaredField("userAge");
            userAgeField.setAccessible(true);
            userAgeField.set(user, 30);
            int userAge = userAgeField.getInt(user);
            System.out.println("userName = " + userName);
            System.out.println("userAge = " + userAge);
        }catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
