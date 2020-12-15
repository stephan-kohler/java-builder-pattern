package nl.avisi.demo.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DomainTestUtil {

    public static Field getDeclaredField(Class<?> type, String name) {
        try {
            return type.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            if (type.getSuperclass() != null && !type.getSuperclass().equals(Object.class)) {
                return getDeclaredField(type.getSuperclass(),name);
            }
        }
        return null;
    }

    public static Method getDeclaredMethod(Class<?> type, String name) {
        try {
            return type.getDeclaredMethod(name);
        } catch (NoSuchMethodException e) {
            if (type.getSuperclass() != null && !type.getSuperclass().equals(Object.class)) {
                return getDeclaredMethod(type.getSuperclass(),name);
            }
        }
        return null;
    }


    public static List<Field> getDeclaredFields(Class<?> type,List<Field> fields) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null && !type.getSuperclass().equals(Object.class)) {
            return getDeclaredFields(type.getSuperclass(),fields);
        }
        return fields;
    }


    public static List<Method> getDeclaredMethods(Class<?> type,List<Method> methods) {
        methods.addAll(Arrays.asList(type.getDeclaredMethods()));
        if (type.getSuperclass() != null && !type.getSuperclass().equals(Object.class)) {
            return getDeclaredMethods(type.getSuperclass(),methods);
        }
        return methods;
    }

}
