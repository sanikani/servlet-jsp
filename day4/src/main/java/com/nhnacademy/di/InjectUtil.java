package com.nhnacademy.di;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class InjectUtil {

    public static <T> T getObject(Class<T> classType){
        T instance = createInstance(classType);
        Field[] fields = classType.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Autowired.class) != null) {
                Object fieldInstance = createInstance(field.getType());
                field.setAccessible(true);
                try {
                    field.set(instance, fieldInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
