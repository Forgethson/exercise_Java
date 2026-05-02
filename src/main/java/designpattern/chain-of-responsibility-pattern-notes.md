# 责任链模式学习笔记

## 核心概念

责任链模式用于处理一个请求需要经过多个处理节点的场景。

可以概括为：

```text
责任链模式：把多个处理器串成一条链，请求沿着链依次传递。
```

它的核心价值是：

```text
把一大段连续的 if else 校验拆成多个独立处理器。
```

每个处理器只负责自己的判断逻辑，通过后再交给下一个处理器。

## 当前支付校验场景

当前示例中，一笔支付请求需要经过多步校验：

```text
ParamCheckHandler        参数校验
UserStatusCheckHandler   用户状态校验
RiskCheckHandler         风控校验
BalanceCheckHandler      余额校验
```

这些处理器都继承同一个抽象处理器：

```java
public abstract class PayCheckHandler {
    private PayCheckHandler next;

    public PayCheckHandler setNext(PayCheckHandler next) {
        this.next = next;
        return next;
    }

    public final boolean check(PayRequest request) {
        if (!doCheck(request)) {
            return false;
        }
        if (next == null) {
            return true;
        }
        return next.check(request);
    }

    protected abstract boolean doCheck(PayRequest request);
}
```

这里有两个关键点：

```text
next        指向下一个处理器
doCheck()   当前处理器自己的校验逻辑
```

## 链的组装

责任链需要先把多个处理器按顺序串起来：

```java
PayCheckHandler paramCheck = new ParamCheckHandler();
PayCheckHandler userStatusCheck = new UserStatusCheckHandler();
PayCheckHandler riskCheck = new RiskCheckHandler();
PayCheckHandler balanceCheck = new BalanceCheckHandler();

paramCheck
        .setNext(userStatusCheck)
        .setNext(riskCheck)
        .setNext(balanceCheck);
```

这条链的执行顺序是：

```text
参数校验 -> 用户状态校验 -> 风控校验 -> 余额校验
```

调用方只需要调用链头：

```java
boolean result = paramCheck.check(request);
```

调用方不需要关心链中有多少个处理器，也不需要自己逐个调用。

## 失败短路

当前示例采用的是“失败短路”模型。

也就是说：

```text
只要某个处理器校验失败，后面的处理器就不再执行。
```

核心逻辑在这里：

```java
if (!doCheck(request)) {
    return false;
}
```

例如余额不足时，`BalanceCheckHandler` 返回 `false`，整条链的最终结果就是 `false`。

这种方式适合校验类场景：

```text
参数不合法时，不需要继续做用户状态校验
用户不可用时，不需要继续做风控校验
风控不通过时，不需要继续做余额校验
```

## 和 if else 的关系

不用责任链时，支付校验可能会写成：

```java
if (userId == null || userId.trim().isEmpty()) {
    return false;
}
if (amount <= 0) {
    return false;
}
if (!userActive) {
    return false;
}
if (amount > 10000) {
    return false;
}
if (balance < amount) {
    return false;
}
```

问题是：

```text
所有校验逻辑堆在一个方法里
新增校验规则时必须修改主流程
校验顺序和校验细节混在一起
单个校验规则不容易复用
```

使用责任链后，主流程只负责组装顺序：

```java
paramCheck
        .setNext(userStatusCheck)
        .setNext(riskCheck)
        .setNext(balanceCheck);
```

每个处理器负责自己的逻辑。

如果以后要新增黑名单校验，可以新增：

```text
BlackListCheckHandler
```

然后把它插入链中即可。

## 适用场景

责任链模式适合：

```text
一个请求需要经过多个处理步骤
每个步骤可以独立判断是否继续
处理节点可能增加、删除或调整顺序
希望避免主流程里堆大量 if else
调用方不关心具体有哪些处理节点
```

常见场景：

```text
参数校验
登录过滤器
网关拦截器
风控规则
审批流程
支付前置校验
订单状态流转校验
Servlet Filter
Spring MVC Interceptor
Netty ChannelPipeline
```

## 和策略模式的区别

策略模式关注：

```text
同一件事有多种做法，运行时选择其中一种做法。
```

责任链模式关注：

```text
同一个请求要经过多个处理器，按链路顺序逐个处理。
```

一句话区别：

```text
策略模式是“多选一”。
责任链模式是“按顺序过多关”。
```

例如：

```text
选择支付宝支付、微信支付、银行卡支付 -> 策略模式
支付前依次做参数、状态、风控、余额校验 -> 责任链模式
```

## 和模板方法模式的区别

模板方法模式关注：

```text
父类固定流程，子类实现变化步骤。
```

责任链模式关注：

```text
多个独立处理器按顺序组合，链路可以灵活调整。
```

一句话区别：

```text
模板方法的流程通常写死在父类里。
责任链的流程通常通过组装处理器形成。
```

## 总结

```text
责任链模式 = 多个处理器串成链，请求沿链传递，节点决定是否继续。
```

一句话记忆：

```text
把一串 if else 校验拆成一条可组装的处理链。
```
