package designpattern.flyweight;

public class FlyweightPatternDemo {
    public static void main(String[] args) {
        ChessPiece black1 = ChessPieceFactory.getChessPiece("黑");
        ChessPiece black2 = ChessPieceFactory.getChessPiece("黑");
        ChessPiece white1 = ChessPieceFactory.getChessPiece("白");
        ChessPiece white2 = ChessPieceFactory.getChessPiece("白");

        // 颜色是内部状态，由享元对象共享；坐标是外部状态，由调用方每次传入。
        black1.place(1, 1);
        black2.place(2, 3);
        white1.place(4, 5);
        white2.place(6, 7);

        System.out.println("black1 == black2: " + (black1 == black2));
        System.out.println("white1 == white2: " + (white1 == white2));
    }
}
