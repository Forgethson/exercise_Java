package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestJMM {
    static volatile boolean run = true;

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        testVolatile(pool);
        pool.shutdown();
    }

    // 测试可见性
    public static void testVolatile(ExecutorService pool) throws Exception {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                // 去掉 volatile 循环不会停止
                while (run) ;
                // 若线程调用sleep/print，则不存在可见性问题
            }
        });

        TimeUnit.SECONDS.sleep(3);
        run = false;
    }
}
