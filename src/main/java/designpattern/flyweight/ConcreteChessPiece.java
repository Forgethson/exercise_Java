package designpattern.flyweight;

/**
 * 具体享元对象：保存可共享的内部状态。
 */
public class ConcreteChessPiece implements ChessPiece {
    private final String color;

    public ConcreteChessPiece(String color) {
        this.color = color;
    }

    @Override
    public void place(int x, int y) {
        System.out.println(color + "棋落子，x=" + x + ", y=" + y);
    }
}
