package designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理：不手写具体代理类，而是在运行时生成代理对象。
 * 前提是目标对象至少实现一个接口。
 */
public class JdkProxyFactory {
    private JdkProxyFactory() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target) {
        Class<?> targetClass = target.getClass();
        return (T) Proxy.newProxyInstance(
                // 使用目标对象的类加载器加载运行时生成的代理类。
                targetClass.getClassLoader(),
                // JDK 动态代理基于接口生成代理对象，所以这里传入目标对象实现的接口列表。
                targetClass.getInterfaces(),
                // 所有代理方法调用都会转发到 InvocationHandler.invoke()。
                new AccessControlInvocationHandler(target)
        );
    }

    private static class AccessControlInvocationHandler implements InvocationHandler {
        private final Object target;

        private AccessControlInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // method 表示当前被调用的方法，args 表示本次方法调用的参数。
            System.out.println("JDK 动态代理校验：method=" + method.getName());

            // 这里模拟访问控制逻辑：普通用户不能发起超过 1000 元的大额支付。
            if ("pay".equals(method.getName()) && args != null && args.length == 3) {
                String userRole = (String) args[0];
                int amount = (int) args[2];
                if (!"ADMIN".equals(userRole) && amount > 1000) {
                    System.out.println("JDK 动态代理拒绝：普通用户不能发起超过 1000 元的大额支付");
                    return null;
                }
            }

            // 通过反射调用真实对象的方法，这是代理最终转发到目标对象的关键。
            Object result = method.invoke(target, args);
            System.out.println("JDK 动态代理审计：method=" + method.getName() + " 调用结束");
            return result;
        }
    }
}
