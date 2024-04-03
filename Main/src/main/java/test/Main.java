package test;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 直接执行无返回值的Runnable匿名类
        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // 构造FutureTask，传入带返回值的Callable匿名类
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws InterruptedException {
                TimeUnit.SECONDS.sleep(3);
                return 123;
            }
        });
        pool.submit(task);

        System.out.println(task.get());
        pool.shutdown();
    }
}
