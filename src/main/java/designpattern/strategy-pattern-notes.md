# 策略模式学习笔记

## 核心概念

策略模式用于封装一组可替换的算法或业务规则。

可以概括为：

```text
策略模式：同一件事有多种做法，把每种做法封装成一个策略类。
```

它的核心价值是：

```text
用多态替代业务流程中的复杂分支。
```

## 当前优惠计算场景

当前示例中，订单优惠有多种计算方式：

```text
NoDiscountStrategy        无优惠
PercentDiscountStrategy   折扣优惠
FullReduceStrategy        满减优惠
```

它们都实现同一个接口：

```java
public interface DiscountStrategy {
    int calculate(int originalAmount);
}
```

调用方通过上下文统一调用：

```java
DiscountContext context = new DiscountContext(new PercentDiscountStrategy(80));
int finalAmount = context.calculate(100);
```

调用方只依赖 `DiscountStrategy` 的抽象能力，不需要关心具体优惠算法细节。

## 策略模式和 if else

策略模式不是消灭所有 `if else`。

更准确地说：

```text
策略模式不是让判断消失，而是让判断集中在策略选择处，让业务执行逻辑摆脱复杂分支。
```

没有策略模式时，代码可能是：

```java
if ("none".equals(type)) {
    // 无优惠算法
} else if ("percent".equals(type)) {
    // 折扣算法
} else if ("full_reduce".equals(type)) {
    // 满减算法
}
```

问题是：

```text
选择逻辑和具体算法混在一起
主业务流程里充满分支
新增规则时容易改动主流程
```

使用策略模式后，可以把算法拆出去：

```java
DiscountStrategy strategy = strategyMap.get(type);
int finalAmount = strategy.calculate(originalAmount);
```

这里仍然有“选择策略”的过程，但它被集中到了策略选择处。

业务计算过程只剩：

```text
找到策略
执行策略
```

## 使用 Map 注册策略

真实项目里经常用 `Map` 注册策略：

```java
Map<String, DiscountStrategy> strategyMap = new HashMap<>();
strategyMap.put("none", new NoDiscountStrategy());
strategyMap.put("percent", new PercentDiscountStrategy(80));
strategyMap.put("full_reduce", new FullReduceStrategy(100, 30));
```

使用时：

```java
DiscountStrategy strategy = strategyMap.get(type);
int finalAmount = strategy.calculate(originalAmount);
```

这样主业务流程里不需要到处写复杂 `if else`。

## 适用场景

策略模式适合：

```text
同一件事有多种不同做法
这些做法可以互相替换
算法或规则经常变化
业务流程中出现大量用于选择算法的 if else
调用方只关心结果，不关心具体实现
```

常见场景：

```text
优惠计算策略
运费计算策略
支付渠道策略
计费策略
会员等级折扣策略
风控评分策略
佣金计算策略
排序算法
压缩算法
加密算法
```

## 和工厂模式的关系

策略模式关注：

```text
不同算法如何封装和替换
```

工厂模式关注：

```text
对象如何创建
```

它们经常一起使用：

```text
工厂负责根据 type 找到或创建策略对象
策略负责执行具体算法
```

例如：

```text
DiscountStrategyFactory.get(type) -> DiscountStrategy
DiscountStrategy.calculate(amount) -> finalAmount
```

## 总结

```text
策略模式 = 把一组可替换算法封装起来，通过统一接口调用
```

一句话记忆：

```text
if else 不一定消失，但应该从业务执行逻辑中移到策略选择逻辑中。
```

