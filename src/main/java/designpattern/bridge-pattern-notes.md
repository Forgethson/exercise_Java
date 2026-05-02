# 桥接模式学习笔记

## 核心概念

桥接模式用于把抽象部分和实现部分分离，让它们可以独立变化。

可以概括为：

```text
桥接模式 = 两个维度各自继承扩展，再通过组合桥接起来。
```

更具体地说：

```text
抽象部分的子类，和其持有的实现部分子类，可以自由组合。
```

## 当前支付通知场景

当前示例有两个变化维度：

```text
支付渠道：支付宝、微信
通知方式：短信、邮件
```

如果不用桥接模式，可能会为每一种组合都写一个类：

```text
AliSmsPayNotify
AliEmailPayNotify
WechatSmsPayNotify
WechatEmailPayNotify
```

如果后续再新增银行卡支付、站内信通知，类数量会继续膨胀。

## 第一个维度：支付渠道

抽象部分：

```text
PayNotify
```

具体子类：

```text
AliPayNotify
WechatPayNotify
```

这个维度负责表达：

```text
是哪一种支付渠道的通知
```

## 第二个维度：通知方式

实现部分：

```text
MessageSender
```

具体子类：

```text
SmsMessageSender
EmailMessageSender
```

这个维度负责表达：

```text
通知通过什么方式发送
```

## 桥接点

桥接点在 `PayNotify` 中：

```java
protected final MessageSender messageSender;
```

`PayNotify` 通过组合持有 `MessageSender`。

这让两个维度可以自由组合：

```java
new AliPayNotify(new SmsMessageSender());
new AliPayNotify(new EmailMessageSender());
new WechatPayNotify(new SmsMessageSender());
new WechatPayNotify(new EmailMessageSender());
```

可以理解为：

```text
支付渠道子类 + 通知方式子类 = 一个具体组合能力
```

## 扩展方式

如果新增支付渠道：

```text
BankPayNotify
```

只需要新增 `PayNotify` 的子类。

如果新增通知方式：

```text
AppMessageSender
```

只需要新增 `MessageSender` 的实现类。

然后可以自由组合：

```java
new BankPayNotify(new AppMessageSender());
```

这就是桥接模式减少类爆炸的价值。

## 和适配器模式的区别

适配器模式通常是后补的：

```text
已有接口不兼容，所以加一层适配器转换接口。
```

桥接模式通常是设计时就拆分维度：

```text
发现有两个变化维度，所以提前拆开并用组合连接。
```

可以这样区分：

```text
适配器：解决接口不兼容
桥接：解决多维度变化导致类爆炸
```

## 和装饰器模式的区别

装饰器模式关注：

```text
在原对象基础上叠加额外功能
```

桥接模式关注：

```text
把两个独立变化维度拆开，并让它们自由组合
```

例如：

```text
装饰器：给支付能力叠加日志、风控、耗时统计
桥接：让支付渠道和通知方式自由组合
```

## 总结

```text
桥接模式 = 拆分两个变化维度 + 通过组合建立桥梁 + 避免组合类爆炸
```

一句话记忆：

```text
抽象部分的子类，和其持有的实现部分子类，可以自由组合。
```

