# 组合模式学习笔记

## 核心概念

组合模式用于处理树形结构。

可以概括为：

```text
组合模式：用统一接口处理树形结构中的叶子节点和组合节点。
```

它的核心特点是：

```text
叶子节点和组合节点实现同一个抽象接口，调用方可以用统一方式处理它们。
```

## 当前目录树场景

当前示例使用文件系统来演示组合模式。

共同接口：

```text
FileComponent
```

叶子节点：

```text
FileLeaf
```

组合节点：

```text
DirectoryComposite
```

对应关系：

```text
文件 FileLeaf                  -> 叶子节点
目录 DirectoryComposite        -> 组合节点
文件和目录共同实现 FileComponent
```

## 叶子节点

叶子节点没有子节点，只负责执行自己的具体行为。

当前代码：

```java
public class FileLeaf implements FileComponent {
    @Override
    public void show(int depth) {
        System.out.println(indent(depth) + "- " + name);
    }
}
```

可以理解为：

```text
叶子节点：没有 children，自己处理自己的逻辑。
```

## 组合节点

组合节点可以包含其他节点。

当前代码：

```java
public class DirectoryComposite implements FileComponent {
    private final List<FileComponent> children = new ArrayList<>();
}
```

因为 `children` 的类型是 `FileComponent`，所以目录里既可以放文件，也可以放目录：

```text
DirectoryComposite
  -> FileLeaf
  -> DirectoryComposite
       -> FileLeaf
```

组合节点的核心职责是：

```text
管理子节点
递归转发行为
```

当前代码：

```java
for (FileComponent child : children) {
    child.show(depth + 1);
}
```

## 调用方统一处理

调用方只面向共同接口：

```java
root.show(0);
```

调用方不需要关心 `root` 下面到底是：

```text
文件
目录
多层目录
```

每个节点都会按照统一接口递归处理。

## 当前示例结构

```text
root
  README.md
  src
    Main.java
    test
      DemoTest.java
```

这棵树中：

```text
README.md、Main.java、DemoTest.java 是叶子节点
root、src、test 是组合节点
```

## 适用场景

组合模式适合整体和部分具有一致操作的树形结构。

常见场景：

```text
文件系统：文件 / 目录
组织架构：员工 / 部门
菜单系统：菜单项 / 菜单组
权限系统：单个权限 / 权限组
UI 组件：按钮 / 容器
商品分类：商品 / 分类
```

## 总结

```text
组合模式 = 共同接口 + 叶子节点 + 组合节点 + 递归树结构
```

一句话记忆：

```text
叶子节点负责具体行为，组合节点负责管理子节点并递归转发行为。
```

