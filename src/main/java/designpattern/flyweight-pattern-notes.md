# 享元模式学习笔记

## 核心概念

享元模式用于共享可复用对象，减少大量相似对象带来的内存开销。

可以概括为：

```text
享元模式 = 共享内部状态 + 外部传入变化状态 + 工厂缓存复用对象
```

它适合系统中存在大量相似对象，并且这些对象有一部分状态可以共享的场景。

## 当前棋子场景

当前示例使用围棋棋子来演示享元模式。

棋子的颜色只有两种：

```text
黑
白
```

颜色可以被共享。

棋子落在哪个位置不同：

```text
x 坐标
y 坐标
```

坐标不适合放在共享对象里，而是每次使用时由调用方传入。

## 内部状态

内部状态是可以被共享的状态。

当前示例中：

```java
private final String color;
```

`color` 保存在 `ConcreteChessPiece` 中。

黑棋对象只需要一个，白棋对象也只需要一个。

```text
黑棋对象：保存 color=黑
白棋对象：保存 color=白
```

## 外部状态

外部状态是每次使用时不同、不能共享的状态。

当前示例中：

```java
void place(int x, int y);
```

`x` 和 `y` 不保存在棋子对象里，而是每次落子时由调用方传入。

```java
black1.place(1, 1);
black2.place(2, 3);
```

虽然 `black1` 和 `black2` 是同一个黑棋对象，但它们可以被放在不同坐标。

## 享元工厂

享元工厂负责缓存和复用对象。

当前示例：

```java
private static final Map<String, ChessPiece> CACHE = new HashMap<>();
```

获取棋子时，先从缓存里找：

```java
ChessPiece chessPiece = CACHE.get(color);
```

如果缓存没有，才创建新对象并放入缓存：

```java
chessPiece = new ConcreteChessPiece(color);
CACHE.put(color, chessPiece);
```

所以多次获取同一种颜色的棋子，拿到的是同一个对象：

```java
ChessPiece black1 = ChessPieceFactory.getChessPiece("黑");
ChessPiece black2 = ChessPieceFactory.getChessPiece("黑");

black1 == black2 // true
```

## 适用场景

享元模式适合：

```text
系统中有大量相似对象
对象创建数量非常多
对象中有可共享的内部状态
变化状态可以从对象中剥离，作为外部状态传入
```

常见场景：

```text
棋子对象
字符对象
连接池
线程池
缓存池
权限标识
商品规格属性
```

## 注意点

享元对象应该尽量保存不可变的内部状态。

外部状态不要放进享元对象里，否则共享对象会被污染。

例如棋子坐标如果放进 `ConcreteChessPiece` 中：

```text
同一个黑棋对象被复用时，后一次坐标会覆盖前一次坐标。
```

所以：

```text
共享的状态放对象里
变化的状态调用时传入
```

## 和单例模式的区别

单例模式关注：

```text
一个类只有一个实例
```

享元模式关注：

```text
一类可共享对象按 key 缓存复用
```

当前示例中：

```text
黑棋一个实例
白棋一个实例
```

它不是整个系统只有一个棋子对象，而是不同 key 对应不同共享对象。

## 总结

```text
享元模式 = 把可共享的内部状态缓存起来，把变化的外部状态从对象中剥离出去
```

一句话记忆：

```text
相同的对象复用，不同的状态外传。
```

