package designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 动态代理：运行时生成目标类的子类代理对象。
 * 它不要求目标类实现接口，但目标类不能是 final，待增强的方法也不能是 final/private。
 */
public class CglibProxyFactory {
    private CglibProxyFactory() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> targetClass) {
        Enhancer enhancer = new Enhancer();
        // CGLIB 通过继承目标类生成代理类，所以这里设置目标类为父类。
        enhancer.setSuperclass(targetClass);
        // 代理对象的方法调用会进入 MethodInterceptor.intercept()。
        enhancer.setCallback(new AccessControlMethodInterceptor());
        return (T) enhancer.create();
    }

    private static class AccessControlMethodInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("CGLIB 代理校验：method=" + method.getName());

            if ("pay".equals(method.getName()) && args != null && args.length == 3) {
                String userRole = (String) args[0];
                int amount = (int) args[2];
                if (!"ADMIN".equals(userRole) && amount > 1000) {
                    System.out.println("CGLIB 代理拒绝：普通用户不能发起超过 1000 元的大额支付");
                    return null;
                }
            }

            // 调用父类原始方法，也就是执行目标类中的真实业务逻辑。
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("CGLIB 代理审计：method=" + method.getName() + " 调用结束");
            return result;
        }
    }
}
