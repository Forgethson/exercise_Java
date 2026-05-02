# 装饰器模式学习笔记

## 核心概念

装饰器模式用于在不修改原有类的情况下，动态地给对象增加额外功能。

可以概括为：

```text
同接口 + 持有同接口对象 + 调用前后增强 + 层层组合
```

它不是继承式增强，而是组合式增强。

## 当前支付场景

当前示例中，基础支付能力是：

```text
BasicPayService
```

额外增强能力包括：

```text
RiskCheckPayDecorator   风控校验
LogPayDecorator         支付日志
TimerPayDecorator       耗时统计
```

这些类都实现同一个接口：

```java
public interface PayService {
    void pay(String userId, int amount);
}
```

所以调用方始终只面向 `PayService`。

## 组合方式

装饰器内部会持有一个同接口对象：

```java
public abstract class PayServiceDecorator implements PayService {
    protected final PayService payService;
}
```

这就是装饰器能够层层组合的关键。

当前 demo 的组合方式是：

```java
PayService payService = new TimerPayDecorator(
        new LogPayDecorator(
                new RiskCheckPayDecorator(
                        new BasicPayService()
                )
        )
);
```

可以理解成：

```text
TimerPayDecorator
  -> LogPayDecorator
    -> RiskCheckPayDecorator
      -> BasicPayService
```

## 调用链

调用方执行：

```java
payService.pay("USER_001", 100);
```

实际调用链是：

```text
TimerPayDecorator.pay()
  -> LogPayDecorator.pay()
    -> RiskCheckPayDecorator.pay()
      -> BasicPayService.pay()
```

每一层都可以在调用下一级对象之前或之后增加自己的逻辑。

例如：

```text
RiskCheckPayDecorator：在支付前做风控校验
LogPayDecorator：在支付前后打印日志
TimerPayDecorator：在整个调用链外层统计耗时
```

## 和适配器模式的区别

适配器模式关注：

```text
接口不兼容，如何转换接口
```

装饰器模式关注：

```text
接口不变，如何增强功能
```

例如：

```text
适配器：把不同支付 SDK 统一成 PayService
装饰器：给 PayService 增加日志、风控、耗时统计
```

## 和继承的区别

继承式增强通常在编译期确定：

```text
一个子类固定继承一个父类
```

装饰器是运行期组合：

```text
可以按需要选择不同装饰器，也可以调整装饰器顺序
```

例如：

```text
Timer -> Log -> RiskCheck -> Basic
Log -> RiskCheck -> Basic
RiskCheck -> Basic
```

这些组合都可以通过构造方法灵活拼出来。

## 总结

```text
装饰器模式 = 保持接口不变 + 用组合方式动态增强对象能力
```

它适合功能可以拆分、可以叠加、组合顺序可能变化的场景。

