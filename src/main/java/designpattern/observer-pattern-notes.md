# 观察者模式学习笔记

## 核心概念

观察者模式用于处理一个对象发生变化后，需要通知多个对象的场景。

可以概括为：

```text
观察者模式：事件源对象持有一组观察者，事件发生时统一通知它们。
```

它的核心价值是：

```text
把事件触发和事件响应解耦。
```

事件源只负责发布事件，不需要关心每个观察者内部具体怎么处理。

## 当前支付成功场景

当前示例中，订单支付成功后，需要触发多个后续动作：

```text
SmsNotifyObserver     发送短信通知
PointRewardObserver   增加用户积分
CouponSendObserver    发放优惠券
PayLogObserver        记录支付日志
```

这些观察者都实现同一个接口：

```java
public interface PaySuccessObserver {
    void onPaySuccess(OrderPayEvent event);
}
```

支付成功事件用 `OrderPayEvent` 表示：

```java
public class OrderPayEvent {
    private final String orderId;
    private final String userId;
    private final int amount;
}
```

事件对象负责携带观察者需要的数据。

## 事件源持有观察者属性

观察者不是事件源的子类，而是事件源对象中的属性集合。

当前示例中的关键代码是：

```java
public class OrderPaySubject {
    private final List<PaySuccessObserver> observers = new ArrayList<>();
}
```

这里可以理解为：

```text
OrderPaySubject       事件源 / 被观察者
observers             事件源持有的一组观察者属性
PaySuccessObserver    观察者接口
OrderPayEvent         事件对象
```

所以观察者模式的核心不是继承，而是：

```text
组合 + 回调
```

事件源组合了一组观察者，事件发生时回调观察者的方法。

## 注册观察者

观察者需要先注册到事件源中：

```java
OrderPaySubject orderPaySubject = new OrderPaySubject();

orderPaySubject.addObserver(new SmsNotifyObserver());
orderPaySubject.addObserver(new PointRewardObserver());
orderPaySubject.addObserver(new CouponSendObserver());
orderPaySubject.addObserver(new PayLogObserver());
```

注册完成后，`OrderPaySubject` 内部的 `observers` 集合就持有了这些观察者对象。

如果后续不想再通知某个观察者，也可以移除：

```java
orderPaySubject.removeObserver(observer);
```

## 事件触发

支付成功时，调用事件源的方法：

```java
OrderPayEvent event = new OrderPayEvent("ORDER_001", "USER_001", 168);
orderPaySubject.paySuccess(event);
```

事件源内部会统一通知所有观察者：

```java
private void notifyObservers(OrderPayEvent event) {
    for (PaySuccessObserver observer : observers) {
        observer.onPaySuccess(event);
    }
}
```

执行流程是：

```text
1. 外部把观察者注册到事件源对象里
2. 事件源发生支付成功事件
3. 事件源遍历 observers 属性
4. 挨个调用 observer.onPaySuccess(event)
5. 每个观察者执行自己的响应逻辑
```

## 和直接调用的区别

不用观察者模式时，支付成功逻辑可能会写成：

```java
payOrder();
sendSms();
addPoints();
sendCoupon();
writePayLog();
```

问题是：

```text
支付主流程知道太多后续动作
新增后续动作时必须修改支付主流程
各个后续动作和支付逻辑耦合在一起
某个后续动作的变化容易影响主流程
```

使用观察者模式后，支付主流程只负责发布事件：

```java
orderPaySubject.paySuccess(event);
```

后续要新增“发送站内信”，只需要新增观察者：

```text
InboxMessageObserver
```

然后注册到事件源即可。

## 适用场景

观察者模式适合：

```text
一个事件发生后，需要通知多个对象
事件源不想关心具体有哪些响应动作
响应动作经常增加或减少
希望把主流程和后续动作拆开
多个对象依赖同一个事件变化
```

常见场景：

```text
订单支付成功通知
用户注册成功通知
消息订阅
事件发布
GUI 事件监听
配置变更监听
缓存更新通知
Spring ApplicationEvent
MQ 消息消费
```

## 和责任链模式的区别

责任链模式关注：

```text
一个请求按顺序经过多个处理器，通常中途可以停止。
```

观察者模式关注：

```text
一个事件发生后，通知多个观察者，通常每个观察者都会收到。
```

一句话区别：

```text
责任链模式是“按顺序过多关”。
观察者模式是“一件事通知多个人”。
```

例如：

```text
支付前依次做参数、状态、风控、余额校验 -> 责任链模式
支付成功后通知短信、积分、优惠券、日志 -> 观察者模式
```

## 和模板方法模式的区别

模板方法模式关注：

```text
父类固定流程，子类实现变化步骤。
```

观察者模式关注：

```text
事件源持有观察者集合，事件发生时回调观察者。
```

一句话区别：

```text
模板方法模式靠继承复用流程。
观察者模式靠组合和回调通知对象。
```

## 总结

```text
观察者模式 = 事件源对象持有一组观察者属性，事件发生时统一回调它们。
```

一句话记忆：

```text
一件事发生，通知一批人各自处理。
```
