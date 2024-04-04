package test;


import java.util.concurrent.*;

public class UseThreadPool {
    public static void main(String[] args) {
        // 固定线程个数
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ExecutorService pool1 = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        LinkedBlockingDeque<Runnable> waitQueue = new LinkedBlockingDeque<>();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                10,
                20,
                10,
                TimeUnit.MINUTES,
                waitQueue,
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        // 满了就阻塞等待
                        try {
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        for (int i = 0; i < 10; i++) {
            poolExecutor.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("这里是线程：" + Thread.currentThread().getName());
            });

            pool.shutdown();
            pool1.shutdown();
            poolExecutor.shutdown();

        }
    }
}
