package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflection {
    public static void main(String[] args) throws Exception {
        // 获取Class对象
        Class<?> clazz = Class.forName("test.Student");
        System.out.println(clazz);

        // 用无参构造函数创建对象
        Student student = (Student) clazz.newInstance();
        System.out.println(student);

        // 根据参数类型获取构造函数，并创建对象
        Constructor<?> constructor = clazz.getConstructor(int.class, String.class, int.class);
        System.out.println(constructor.newInstance(1, "ming", 2));

        // 获取字段（由于是私有的，用declared）
        Field field = clazz.getDeclaredField("score");
        field.setAccessible(true); // 暴S破
        field.set(student, 99);
        System.out.println(student);

        // 获取成员方法
        Method method = clazz.getMethod("show");
        method.invoke(student);
    }
}
