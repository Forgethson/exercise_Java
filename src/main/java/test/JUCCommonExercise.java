package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class JUCCommonExercise {
    private static volatile int count = 0;
    private static final int maxNumber = 100;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition even = lock.newCondition();
        Condition odd = lock.newCondition();
        ExecutorService pool = Executors.newFixedThreadPool(5);

        // 偶数线程
        pool.submit(() -> {
            while (count < maxNumber) {
                lock.lock();
                try {
                    // 双重锁
                    if (count >= maxNumber) {
                        break;
                    }
                    while (count % 2 != 0) {
                        even.await();
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                    System.out.println("even线程" + Thread.currentThread().getName() + "打印了：" + count);
                    count++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    odd.signal();
                    lock.unlock();
                }
            }
        });

        // 奇数线程
        pool.submit(() -> {
            while (count < maxNumber) {
                lock.lock();
                try {
                    // 双重锁
                    if (count >= maxNumber) {
                        break;
                    }
                    while (count % 2 == 0) {
                        odd.await();
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                    System.out.println("odd线程" + Thread.currentThread().getName() + "打印了：" + count);
                    count++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    even.signal();
                    lock.unlock();
                }
            }
        });


        TimeUnit.SECONDS.sleep(5);
        lock.lock();
        System.out.println("主线程获取锁成功");
        lock.unlock();
        pool.shutdown();
    }
}
