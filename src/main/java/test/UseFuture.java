package test;

import java.util.concurrent.*;

public class UseFuture {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 耗时计算任务2
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 99995;
        });
        pool.submit(task);

        // 主线程：计算任务1，耗时1s
        int num = 0;
        for (int i = 0; i < 5; i++) {
            TimeUnit.MILLISECONDS.sleep(200);
            num++;
        }

        try {
            int t = task.get(3000, TimeUnit.MILLISECONDS);
            System.out.println("子线程计算成功，get=" + t);
            System.out.printf("res=%d\n", num + t);
        } catch (Exception e) {
            System.out.println("子线程计算失败，err=" + e);
        }

        pool.shutdown();
    }
}
