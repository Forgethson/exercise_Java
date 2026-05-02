# 工厂模式学习笔记

## 三种工厂模式的核心区别

### 简单工厂

简单工厂是一个具体工厂类，根据传入参数决定创建并返回哪个具体产品对象。

```text
一个工厂 + 一个创建方法 + 参数判断 + 多个产品
```

典型结构：

```text
PayFactory.create("ali")    -> AliPayService
PayFactory.create("wechat") -> WechatPayService
```

优点是把对象创建逻辑集中管理，调用方不用直接 `new` 具体类。

缺点是新增产品时，通常要修改同一个工厂类。

## 工厂方法

工厂方法模式里，工厂通常也是接口或抽象类。

```java
public interface PayFactory {
    PayService create();
}
```

每个具体工厂负责创建一种具体产品：

```text
AliPayFactory    -> AliPayService
WechatPayFactory -> WechatPayService
```

可以概括为：

```text
多个工厂 + 每个工厂一个创建方法 + 每个工厂创建一个产品
```

工厂方法模式抽象的是“一个创建产品的方法”。

## 抽象工厂

抽象工厂模式里，工厂也是接口或抽象类，但它定义的是一组产品的创建方法。

```java
public interface PaymentFactory {
    PayService createPayService();

    RefundService createRefundService();
}
```

每个具体工厂负责创建同一产品族下的一组具体产品：

```text
AliPaymentFactory
  -> AliPayService
  -> AliRefundService

WechatPaymentFactory
  -> WechatPayService
  -> WechatRefundService
```

可以概括为：

```text
多个工厂 + 每个工厂多个创建方法 + 每个工厂创建一个产品族
```

抽象工厂模式抽象的是“一组产品的创建工厂”。

## 工厂方法和抽象工厂为什么容易混淆

工厂方法模式和抽象工厂模式里，工厂都可以是接口。

真正区别不是“有没有工厂接口”，而是：

```text
工厂方法：一个工厂方法，创建一个产品
抽象工厂：多个工厂方法，创建一个产品族
```

所以：

```text
工厂方法模式：抽象的是一个创建产品的方法
抽象工厂模式：抽象的是一组产品的创建工厂
```

## 对比总结

| 模式 | 工厂形式 | 创建对象 | 扩展方式 |
| --- | --- | --- | --- |
| 简单工厂 | 一个具体工厂类 | 根据参数创建不同产品 | 新增产品通常要改工厂类 |
| 工厂方法 | 一个工厂接口，多个具体工厂 | 一个具体工厂创建一个产品 | 新增产品时新增具体工厂 |
| 抽象工厂 | 一个工厂接口，多个具体工厂 | 一个具体工厂创建一个产品族 | 新增产品族时新增具体工厂 |

