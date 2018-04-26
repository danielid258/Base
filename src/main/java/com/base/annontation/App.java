package com.base.annontation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @desc: Created by Fengyun on 4/10/2018.
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object instance = Class.forName("com.base.annontation.Student").newInstance();
        Method[] methods = instance.getClass().getDeclaredMethods();
        for (Method method :
                methods) {
            if (method.isAnnotationPresent(ValueBind.class)) {
                ValueBind valueBind = method.getAnnotation(ValueBind.class);
                String type = String.valueOf(valueBind.type());
                String value = valueBind.value();

                if (type.equals("INT"))
                    try {
                        //调用 instance的 method方法 把ValueBind注解中value的值 set到注解对应的 Student的属性上
                        method.invoke(instance, new Integer[]{new Integer(value)});
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                if (type.equals("STRING")) {
                    try {
                        method.invoke(instance, new String[]{value});
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Student student = (Student) instance;
        System.out.println(student.getStuNO());
        System.out.println(student.getName());
        System.out.println(student.getAge());

    }
}
