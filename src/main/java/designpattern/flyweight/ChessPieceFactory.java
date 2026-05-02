package designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂：缓存可共享对象，避免重复创建。
 */
public class ChessPieceFactory {
    private static final Map<String, ChessPiece> CACHE = new HashMap<>();

    private ChessPieceFactory() {
    }

    public static ChessPiece getChessPiece(String color) {
        return CACHE.computeIfAbsent(color, ConcreteChessPiece::new);
    }
}
