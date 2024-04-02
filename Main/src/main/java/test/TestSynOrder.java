package src.main.java.test;

/**
 * 获取锁的顺序，是按照等待锁的顺序的逆序来的
 * */

public class TestSynOrder {
    public synchronized static void order() {
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        if (Thread.currentThread().getName().equals("Thread-0"))
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(TestSynOrder::order, "Thread-0").start();

        for (int i = 1; i < 100; i++) {
            new Thread(TestSynOrder::order, "Thread-" + i).start();
            Thread.sleep(10);
        }
    }
}
