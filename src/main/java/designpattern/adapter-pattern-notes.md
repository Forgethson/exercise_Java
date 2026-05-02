# 适配器模式学习笔记

## 核心概念

适配器模式用于解决接口不兼容的问题。

可以概括为：

```text
适配器模式：把一个类的接口转换成调用方期望的另一个接口。
```

更工程化的理解是：

```text
通过提供统一的目标接口，屏蔽底层不同接口的差异，让调用方用同一种方式访问不同实现。
```

## 当前支付场景

当前示例中，业务层希望统一使用：

```java
public interface PayService {
    void pay(String userId, int amount);
}
```

但底层不同支付 SDK 的接口并不一致：

```text
AliPaySdk      -> aliPay(String uid, BigDecimal amount)
WechatPaySdk   -> wxPay(String openId, int cents)
BankPaySdk     -> bankPay(BankCard card, int yuan)
```

这些 SDK 的差异包括：

```text
方法名不同
参数名称不同
参数类型不同
金额单位不同
```

如果业务层直接调用这些 SDK，就会到处充满渠道差异。

## 适配器的作用

适配器负责把统一接口调用转换成底层 SDK 调用。

```text
PayService -> Adapter -> 第三方 SDK / 旧系统接口
```

在当前示例中：

```text
AliPayAdapter     -> 适配 AliPaySdk
WechatPayAdapter  -> 适配 WechatPaySdk
BankPayAdapter    -> 适配 BankPaySdk
```

调用方只需要面向统一接口：

```java
PayService aliPay = new AliPayAdapter(new AliPaySdk());
PayService wechatPay = new WechatPayAdapter(new WechatPaySdk());
PayService bankPay = new BankPayAdapter(new BankPaySdk());

aliPay.pay("USER_001", 100);
wechatPay.pay("USER_001", 100);
bankPay.pay("USER_001", 100);
```

调用方不需要关心底层 SDK 的方法名、参数类型和金额单位。

## 关键结论

```text
适配器不是为了改变底层能力，而是为了统一上层调用方式。
```

它的核心价值是：

```text
接口转换
隔离变化
兼容旧系统或第三方 SDK
降低调用方复杂度
```

## 适用场景

适配器模式常见于：

```text
旧系统接口和新系统接口不一致
第三方 SDK 接口风格不统一
多个厂商能力类似，但方法名、参数结构不同
系统重构时，不想一次性改完所有调用方
```

## 和代理模式的区别

适配器模式关注：

```text
接口不兼容，如何转换接口
```

代理模式关注：

```text
接口通常相同，如何在调用前后增加控制逻辑
```

例如：

```text
适配器：把 AliPaySdk / WechatPaySdk / BankPaySdk 统一成 PayService
代理：在 PayService.pay() 前后增加日志、权限、事务、耗时统计
```

## 总结

```text
适配器模式 = 统一目标接口 + 适配底层差异
```

调用方看到的是统一接口，适配器内部负责处理不同底层实现的差异。

