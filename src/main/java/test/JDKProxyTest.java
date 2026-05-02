package test;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyTest {
    public static void main(String[] args) throws Exception {
        // 定义目标对象
        Person p = new Person();
        // 定义InvocationHandler
        InvocationHandlerImpl handler = new InvocationHandlerImpl(p);
        // 代理对象proxyInstance只能强转成它们共同的接口类型Action，故动态代理所代理的是接口部分的方法
        // 构造代理对象的方式1：
        Action proxyInstance = (Action) Proxy.newProxyInstance(p.getClass().getClassLoader(), p.getClass().getInterfaces(), handler);
        // 构造代理对象的方式2：
        Action proxyInstance2 = (Action) Proxy.newProxyInstance(p.getClass().getClassLoader(), new Class[]{Action.class}, handler);

        // 通过Person对象得到其实现的接口列表：[Action]
        Class<?>[] i1 = p.getClass().getInterfaces();
        // 通过Action接口得到其实现的接口列表：[]（没有父接口）
        Class<?>[] i2 = Action.class.getInterfaces();
        // 通过Person类得到其实现的接口列表：[Action]
        Class<?>[] i3 = Person.class.getInterfaces();
        // 直接构造[Action]
        Class<?>[] i4 = new Class[]{Action.class};
        proxyInstance.sleep();
        proxyInstance2.sleep();

    }
}

interface Action {
    void sleep();
}

class Person implements Action {
    @Override
    public void sleep() {
        System.out.println("Person is sleeping.");
    }

    public void eat() {
        System.out.println("Person is eating.");
    }
}


@AllArgsConstructor
class InvocationHandlerImpl implements InvocationHandler {
    private final Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = null;
        try {
            System.out.println("前置通知");
            res = method.invoke(target, args);
            System.out.println("返回通知");
        } catch (InvocationTargetException e) {
            System.out.println("异常通知");
        } finally {
            System.out.println("后置通知");
        }
        return res;
    }
}