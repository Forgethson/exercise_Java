package src.main.java.test;

import java.util.HashMap;

/**
 * HashMap并发读，单线程写测试
*/

public class TestMap {
    HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        TestMap testMap = new TestMap();

        Thread getThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    Integer integer = testMap.map.get(1);
                    System.out.println(integer);
                }).start();
            }
        });
        Thread putThread = new Thread(() -> {
            new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    testMap.map.put(1, i);
                }
            }).start();
        });

        getThread.start();
        putThread.start();
    }
}
