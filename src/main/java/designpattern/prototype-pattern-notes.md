# 原型模式学习笔记

## 核心概念

原型模式通过复制已有对象来创建新对象。

可以概括为：

```text
不从零 new 一个对象，而是 clone 一个已有对象。
```

它适合这些场景：

```text
对象创建成本较高
对象字段较多
新对象和已有对象大部分内容相同
需要快速生成相似对象
```

## Object.clone()

`super.clone()` 调用的是 `Object` 类中的 `clone()` 方法。

但 Java 默认提供的 `Object.clone()` 不是深拷贝，而是浅拷贝。

```text
Object.clone() 默认做 shallow copy。
```

浅拷贝会复制对象本身，并把字段值复制一份：

```text
基本类型字段：复制值
引用类型字段：复制引用地址，不复制引用指向的对象
```

所以浅拷贝后：

```text
原对象 != 新对象
原对象.address == 新对象.address
```

也就是说，两个对象会共享同一个内部引用对象。

## Cloneable

`Cloneable` 是一个标记接口。

它本身没有方法，但它告诉 `Object.clone()`：

```text
这个类允许被 clone。
```

如果一个类没有实现 `Cloneable`，直接调用 `super.clone()` 会抛出 `CloneNotSupportedException`。

## 浅拷贝

对应代码：

```text
designpattern.prototype.ShallowCopyOrder
```

核心代码：

```java
@Override
public ShallowCopyOrder clone() {
    try {
        return (ShallowCopyOrder) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new AssertionError(e);
    }
}
```

这里直接使用 `super.clone()`，所以只复制订单对象本身。

订单内部的 `Address` 不会被复制，新旧订单共享同一个 `Address`。

因此：

```java
copied.getAddress().setCity("上海");
```

会同时影响原订单和复制出来的新订单。

## 深拷贝

对应代码：

```text
designpattern.prototype.DeepCopyOrder
```

核心代码：

```java
@Override
public DeepCopyOrder clone() {
    try {
        DeepCopyOrder copy = (DeepCopyOrder) super.clone();
        copy.address = this.address.clone();
        return copy;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError(e);
    }
}
```

深拷贝需要两步：

```text
1. 复制对象本身
2. 复制对象内部的引用字段
```

在当前例子里：

```java
copy.address = this.address.clone();
```

这行代码会为新订单创建一份新的 `Address`，避免新旧订单共享同一个地址对象。

## Address#clone()

对应代码：

```text
designpattern.prototype.Address#clone
```

`Address` 自己也实现了 `Cloneable`，并重写了 `clone()`：

```java
@Override
public Address clone() {
    try {
        return (Address) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new AssertionError(e);
    }
}
```

当前 `Address` 内部只有 `String` 字段：

```java
private String city;
private String detail;
```

`String` 是不可变对象，所以 `Address` 使用 `super.clone()` 就足够。

如果 `Address` 内部还有可变引用对象，也需要继续手动 clone。

## 深拷贝的递归思想

深拷贝可以理解为：

```text
深拷贝 = 拷贝对象本身 + 递归拷贝对象内部的引用字段
```

例如：

```text
Order -> Address -> GeoLocation
```

真正的深拷贝需要做到：

```text
复制 Order
复制 Address
复制 GeoLocation
```

如果只复制到 `Address`，但没有复制 `GeoLocation`，那么 `GeoLocation` 仍然可能被共享。

## 总结

```text
Object.clone() 默认是浅拷贝
深拷贝需要手动复制引用字段
引用字段自身通常也要实现 clone()
如果引用字段内部还有引用字段，也要继续处理
```

