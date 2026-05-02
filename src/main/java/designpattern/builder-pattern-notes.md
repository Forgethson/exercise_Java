# 建造者模式学习笔记

## 核心概念

建造者模式用于创建复杂对象。

它将复杂对象的构建过程和对象本身分离，让调用方可以按步骤、可读地创建对象。

可以概括为：

```text
建造者模式：一步一步构建复杂对象。
```

## 适用场景

当一个对象有很多字段，尤其是有很多可选字段时，直接使用构造方法会变得不清晰。

例如：

```java
Order order = new Order(
        "ORDER_001",
        "USER_001",
        100,
        "ali",
        "北京市朝阳区",
        true,
        "优惠券A",
        "尽快发货"
);
```

这种写法的问题是：

```text
参数顺序容易写错
可选参数太多
构造方法重载容易膨胀
对象创建过程不清晰
```

使用建造者模式后：

```java
Order order = new Order.Builder()
        .orderNo("ORDER_001")
        .userId("USER_001")
        .amount(100)
        .payType("ali")
        .address("北京市朝阳区")
        .useCoupon(true)
        .couponName("优惠券A")
        .remark("尽快发货")
        .build();
```

这种写法更接近自然语言，字段含义清晰，也不依赖参数顺序。

## 典型结构

```text
Product         要创建的复杂对象
Builder         构建对象的建造者
build()         最终生成对象的方法
```

在当前示例中：

```text
Product: Order
Builder: Order.Builder
build(): 创建并返回 Order
```

## 关键实现点

`Order` 的构造方法通常设为 `private`，避免外部直接 `new`。

```java
private Order(Builder builder) {
    this.orderNo = builder.orderNo;
}
```

`Builder` 的每个设置方法都返回 `this`，从而支持链式调用。

```java
public Builder orderNo(String orderNo) {
    this.orderNo = orderNo;
    return this;
}
```

最后通过 `build()` 创建目标对象。

```java
public Order build() {
    return new Order(this);
}
```

## 和工厂模式的区别

工厂模式关注：

```text
创建哪一种对象
```

建造者模式关注：

```text
一个复杂对象如何被创建出来
```

例如：

```text
工厂模式：根据支付类型创建 AliPayService 还是 WechatPayService
建造者模式：一步一步设置 Order 的订单号、用户、金额、地址、备注等字段
```

## 总结

建造者模式适合字段多、构造过程复杂、可选参数多的对象。

它的核心价值是提升对象创建代码的可读性，并减少构造方法参数顺序错误。

