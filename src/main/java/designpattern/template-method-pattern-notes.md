# 模板方法模式学习笔记

## 核心概念

模板方法模式用于处理流程固定、局部步骤变化的场景。

可以概括为：

```text
父类定流程，子类填步骤。
```

更准确地说：

```text
在抽象父类中定义固定流程，在子类中实现流程里的可变步骤。
```

## 当前支付流程场景

当前示例中，支付流程整体固定：

```text
1. 校验订单
2. 执行扣款
3. 发送通知
```

不同支付渠道的变化点是：

```text
执行扣款
```

例如：

```text
AliPayTemplate       支付宝扣款
WechatPayTemplate    微信扣款
BankPayTemplate      银行卡扣款
```

## 模板方法

模板方法定义在父类中：

```java
public final void pay(String userId, int amount) {
    checkOrder(userId, amount);
    doPay(userId, amount);
    sendNotify(userId, amount);
}
```

这里的 `pay()` 就是模板方法。

它固定了流程顺序：

```text
先校验订单
再执行扣款
最后发送通知
```

通常模板方法会声明为 `final`，避免子类随意修改流程顺序。

## 固定步骤

父类中可以实现通用步骤：

```java
private void checkOrder(String userId, int amount) {
    System.out.println("校验订单，userId=" + userId + ", amount=" + amount);
}
```

这些步骤对子类来说是复用逻辑。

当前示例中的固定步骤：

```text
checkOrder()
sendNotify()
```

## 可变步骤

父类把变化步骤定义成抽象方法：

```java
protected abstract void doPay(String userId, int amount);
```

子类负责实现：

```java
public class AliPayTemplate extends AbstractPayTemplate {
    @Override
    protected void doPay(String userId, int amount) {
        System.out.println("支付宝扣款，userId=" + userId + ", amount=" + amount);
    }
}
```

这样父类控制流程，子类只负责填充变化点。

## 适用场景

模板方法模式适合：

```text
流程顺序固定
大部分步骤可复用
少数步骤因子类不同而变化
希望限制子类修改流程顺序
多个类存在重复流程代码
```

常见场景：

```text
支付流程
下单流程
审批流程
文件导入流程
文件导出流程
任务执行流程
测试框架生命周期
```

## 和策略模式的区别

策略模式关注：

```text
整体算法可以替换
```

模板方法关注：

```text
流程骨架固定，替换其中某些步骤
```

一句话区别：

```text
策略模式换整套算法。
模板方法固定流程，只换其中几步。
```

实现方式上：

```text
策略模式靠组合切换策略。
模板方法靠继承复用流程。
```

## 总结

```text
模板方法模式 = 固定流程骨架 + 子类实现变化步骤
```

一句话记忆：

```text
父类定流程，子类填步骤。
```

