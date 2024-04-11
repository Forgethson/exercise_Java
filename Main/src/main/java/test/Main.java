package test;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 泛型被编译器视为Object，泛型类的Class对象唯一
        One<String> o1 = new One<>();
        One<Integer> o2 = new One<>();
        One<String> o3 = new One<>(String.class);
        Class<?> aClass = o1.getClass();
        Class<?> bClass = o2.getClass();
        Class<?> cClass = o3.getClass();
        System.out.println(aClass);
        System.out.println(bClass);
        System.out.println(cClass);

        Class<String> class1 = String.class;
        // 从String对象拿到的class对象，泛型为 “? extends String”，即String或者其子类（P.S. final修饰没有子类）
        Class<? extends String> class2 = "aa".getClass();
        System.out.println(class1);
        System.out.println(class2);
    }
}

class One<T> {
    public T data;

    public One() {
    }

    public One(Class<T> clazz) throws Exception {
        // 通过反射实例化T
        data = clazz.newInstance();
    }
}
