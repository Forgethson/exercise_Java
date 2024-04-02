package test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        // 获得声明的泛型信息
        Type superType = AppleContainer.class.getGenericSuperclass();
        System.out.println(superType);
        if (superType instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) superType;
            System.out.println(Arrays.toString(paramType.getActualTypeArguments()));
        }
        System.out.println("---------------------------------------------------------");

        // MyData本身没有写死泛型，其中的T会被擦除为Object
        Type type = MyData.class.getGenericSuperclass();
        System.out.println(type);
        System.out.println(type instanceof ParameterizedType);
        System.out.println("---------------------------------------------------------");

        // MyData中的属性idList写死了泛型为String，那么就不会被擦除
        Field field = MyData.class.getDeclaredField("idList");
        Type type1 = field.getGenericType();
        System.out.println(type1);
        System.out.println(type1 instanceof ParameterizedType);
        System.out.println("---------------------------------------------------------");

        // 序列化
        System.out.println("序列化：");
        AppleContainer container = new AppleContainer();
        container.fruit = new Apple();
        container.fruit.name = "苹果";
        container.id = "123";

        MyData<Integer> myData = new MyData<>();
        myData.data = 100;
        myData.idList = new ArrayList<>();
        Gson gson = new Gson();
        String s = gson.toJson(container);
        String s1 = gson.toJson(myData);
        System.out.println(s);
        System.out.println(s1);

        // 反序列化
        System.out.println("反序列化：");
        // FruitContainer<Apple, String> o = gson.fromJson(s, AppleContainer.class.getGenericSuperclass());
        FruitContainer<Apple, String> o1 = gson.fromJson(s, new TypeToken<AppleContainer>() {
        }.getType());
        MyData<Integer> o2 = gson.fromJson(s1, new TypeToken<MyData<Integer>>() {
        }.getType());
        Object o3 = gson.fromJson(s1, MyData.class.getGenericSuperclass());

        System.out.println(o1);
        System.out.println(o2);
    }
}

class Apple extends Fruit {

}

class Fruit {
    public String name;
}

class FruitContainer<T extends Fruit, V> {
    public T fruit;
    public V id;
}

class AppleContainer extends FruitContainer<Apple, String> {

}

class MyData<T> {
    public T data;
    public List<String> idList;
}