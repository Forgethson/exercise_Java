# 外观模式学习笔记

## 核心概念

外观模式为复杂子系统提供一个统一、简单的入口。

可以概括为：

```text
外观模式 = 给复杂子系统封装一个简单入口。
```

它本质上确实是封装一层外观类，但这一层有明确职责：

```text
把一组复杂子系统调用，收敛成一个简单稳定的入口。
```

## 当前下单场景

当前示例中，一个完整下单流程涉及多个子系统：

```text
RiskService       风控检查
StockService      库存锁定
CouponService     优惠券核销
PaymentService    支付扣款
OrderService      订单记录
```

如果没有外观类，调用方需要自己编排这些步骤：

```java
riskService.check(userId, amount);
stockService.lock(skuId);
couponService.use(userId);
paymentService.pay(userId, amount);
orderService.create(userId, skuId, amount);
```

这会让调用方知道太多细节：

```text
下单要先风控
然后锁库存
然后核销优惠券
然后支付
最后创建订单
```

有了外观类后，调用方只需要：

```java
OrderFacade orderFacade = new OrderFacade();
orderFacade.placeOrder("USER_001", "SKU_001", 100);
```

调用方只关心“下单”，内部顺序和子系统细节由 `OrderFacade` 负责。

## 外观模式的价值

外观模式的价值是：

```text
隐藏复杂流程
降低调用方和子系统的耦合
提供统一入口
让复杂系统对外表现得更简单
```

它不是为了增加一层而增加一层，而是为了把复杂调用关系集中管理。

## 和 MVC 的区别

外观模式和 MVC 看起来有点像，因为它们都可能对外提供一个入口。

但它们不是一个维度的东西。

MVC 是架构模式，关注的是：

```text
应用整体如何分层
```

典型结构：

```text
Controller -> Service -> Repository/DAO
```

在后端项目中：

```text
Controller：处理 HTTP 请求、参数、响应
Service：处理业务逻辑
Repository/DAO：处理数据访问
```

外观模式是设计模式，关注的是：

```text
如何封装一组复杂子系统调用
```

例如：

```text
OrderFacade -> RiskService / StockService / CouponService / PaymentService / OrderService
```

所以可以这样区分：

```text
MVC 解决应用怎么分层。
外观模式解决复杂子系统怎么对外提供简单入口。
```

它们可以同时出现：

```text
Controller -> OrderFacade -> RiskService / StockService / PaymentService / OrderService
```

其中：

```text
Controller 属于 MVC 分层
OrderFacade 属于外观模式
```

## 和适配器模式的区别

适配器模式关注：

```text
接口不兼容，如何转换接口
```

外观模式关注：

```text
子系统太复杂，如何提供简单入口
```

例如：

```text
适配器：把不同支付 SDK 统一成 PayService
外观：把风控、库存、优惠券、支付、订单封装成 placeOrder()
```

## 和代理模式的区别

代理模式关注：

```text
控制对某个目标对象的访问
```

外观模式关注：

```text
统一封装一组子系统调用
```

例如：

```text
代理：控制能不能调用支付
外观：统一组织完整下单流程
```

## 总结

```text
外观模式 = 用一个简单入口隐藏复杂子系统
```

它适合调用流程复杂、涉及多个子系统、上层调用方不应该关心内部细节的场景。

