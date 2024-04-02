package src.main.java.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试synchronized关键字
 */

public class testLock {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static int x = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
//            System.out.println("t1 enter");

            try {
                lock.lock();
                System.out.println("t1 得到锁");
//                    Thread.sleep(3000);
                condition.await();
                System.out.println("end lock t1");
            } catch (InterruptedException e) {
                System.out.println("t1 interruptedException");
            } finally {
                lock.unlock();
            }

        });

        Thread thread2 = new Thread(() -> {
//            System.out.println("t2 enter");

            try {
                lock.lock();
                System.out.println("t2 得到锁");
                Thread.sleep(3000);
                condition.signal();
                System.out.println("end lock t2");
            } catch (InterruptedException e) {
                System.out.println("t2 interruptedException");
            } finally {
                lock.unlock();
            }

        });

        Thread thread3 = new Thread(() -> {
//            System.out.println("t3 enter");

            try {
                lock.lock();
                System.out.println("t3 得到锁");
                Thread.sleep(3000);
                System.out.println("end lock t3");
            } catch (InterruptedException e) {
                System.out.println("t3 interruptedException");
            } finally {
                lock.unlock();
            }

        });

//        thread1.start(); // thread1先启动，先拿到o1的锁
//        Thread.sleep(10);
//        thread2.start();
//        Thread.sleep(10);
//        thread3.start();

        int round = 100;
        for (int i = 0; i < round; i++) {
            new Thread(() -> {
                x++;
                System.out.println(x);
            }).start();
        }

        for (int i = 0; i < round; i++) {
            new Thread(() -> {
                x--;
                System.out.println(x);
            }).start();
        }


//        System.out.println("主线程结束");

    }
}
