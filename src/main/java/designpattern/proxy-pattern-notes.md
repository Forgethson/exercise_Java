# 代理模式学习笔记

## 核心概念

代理模式为目标对象提供一个代理对象，由代理对象控制对目标对象的访问。

可以概括为：

```text
代理模式：不直接访问真实对象，而是通过代理对象间接访问真实对象。
```

代理对象和真实对象通常实现同一个接口，调用方以为自己调用的是目标能力，实际上先经过代理对象。

## 当前支付场景

当前示例中，真实支付对象是：

```text
RealPayService
```

静态代理对象是：

```text
PayServiceProxy
```

调用方使用：

```java
PayService payService = new PayServiceProxy(new RealPayService());
```

代理对象负责：

```text
支付前做权限校验
支付后做审计记录
```

真实对象负责：

```text
执行核心支付逻辑
```

## 静态代理

静态代理的代理类是手写的。

```text
代理类在编译期就存在
结构清晰
适合少量明确场景
代理类数量可能变多
```

当前示例：

```text
PayServiceProxy
```

静态代理适合用来理解代理模式的基本结构。

## JDK 动态代理

JDK 动态代理不需要手写具体代理类，而是在运行时生成代理对象。

它基于接口和反射机制实现。

调用流程：

```text
调用方调用代理对象的方法
        ↓
JDK 生成的代理类拦截方法调用
        ↓
进入 InvocationHandler.invoke()
        ↓
在 invoke() 里执行代理逻辑
        ↓
通过 method.invoke(target, args) 反射调用真实对象
```

核心代码：

```java
Proxy.newProxyInstance(
        targetClass.getClassLoader(),
        targetClass.getInterfaces(),
        invocationHandler
);
```

关键限制：

```text
目标对象必须实现接口
```

当前示例：

```text
JdkProxyFactory
JdkProxyDemo
```

## CGLIB 动态代理

CGLIB 动态代理也是运行时生成代理对象，但它不依赖接口。

它通过生成目标类的子类来实现代理。

可以理解为运行时生成类似这样的类：

```java
public class NoInterfacePayServiceProxy extends NoInterfacePayService {
    @Override
    public void pay(String userRole, String userId, int amount) {
        // 调用前增强
        super.pay(userRole, userId, amount);
        // 调用后增强
    }
}
```

核心代码：

```java
Enhancer enhancer = new Enhancer();
enhancer.setSuperclass(targetClass);
enhancer.setCallback(methodInterceptor);
```

关键限制：

```text
不能代理 final 类
不能增强 final 方法
不能增强 private 方法
```

原因是 CGLIB 基于继承和方法重写。

当前示例：

```text
CglibProxyFactory
CglibProxyDemo
NoInterfacePayService
```

## JDK 动态代理和 CGLIB 的区别

两者最终效果类似：

```text
都能在目标方法调用前后插入增强逻辑
```

例如：

```text
权限校验
日志记录
耗时统计
事务控制
缓存处理
审计记录
```

真正区别在实现方式和使用场景：

```text
JDK 动态代理：基于接口生成代理类
CGLIB 动态代理：基于继承生成目标类的子类
```

可以这样记：

```text
目标是一样的：增强方法调用
路径不一样：一个靠接口，一个靠继承
场景不一样：一个要求接口，一个不要求接口
```

| 类型 | 是否需要接口 | 实现方式 | 主要限制 |
| --- | --- | --- | --- |
| JDK 动态代理 | 需要 | 运行时生成接口实现类 | 目标对象必须实现接口 |
| CGLIB 动态代理 | 不需要 | 运行时生成目标类子类 | final 类、final/private 方法不能增强 |

## 和装饰器模式的区别

代理模式和装饰器模式结构很像：

```text
都实现同一个接口
都持有一个目标对象
都可以在调用前后加逻辑
```

但设计意图不同：

```text
装饰器模式：强调功能增强，可以多个装饰器自由组合
代理模式：强调访问控制，由代理对象代表真实对象对外提供访问入口
```

一句话区别：

```text
装饰器：我想给对象多加几个能力
代理：我想控制你能不能访问这个对象
```

## 总结

```text
代理模式 = 代理对象控制真实对象的访问
```

静态代理适合理解结构。

JDK 动态代理适合有接口的统一增强场景。

CGLIB 动态代理适合没有接口、但仍然需要代理增强的场景。

