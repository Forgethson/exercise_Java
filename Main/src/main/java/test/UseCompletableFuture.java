package test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class UseCompletableFuture {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(10);
//        case1(random, pool);
//        case2(random, pool);
        case3(random, pool);

    }

    // case1 测试get()
    public static void case1(Random random, ExecutorService pool) throws Exception {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("开始执行任务1");
                Tools.sleepMS(3000);
                System.out.println("任务1执行结束");
                return random.nextInt(Integer.MAX_VALUE);
            }
        }, pool);
        // get方法和Future接口一样，同样是阻塞获取
        Integer i = completableFuture.get();
        System.out.println("得到结果：" + i);
        pool.shutdown();
        System.out.println("主线程结束");
    }

    public static void case2(Random random, ExecutorService pool) throws InterruptedException {
        // 因为最后是调用thenAccept，无返回值，所以泛型为Void
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("开始执行任务1");
                Tools.sleepMS(3000);
                System.out.println("任务1执行结束");
                return random.nextInt(Integer.MAX_VALUE);
            }
        }, pool).thenAccept(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println("任务1执行结束，结果为：" + i + " (thenAccept)");
            }
        });
        TimeUnit.SECONDS.sleep(5);
        System.out.println("主线程结束");
        pool.shutdown();
    }

    public static void case3(Random random, ExecutorService pool) throws InterruptedException {
        int taskNum = 10;
        // 通过回调得到执行结果，不用遍历CompletableFuture列表，如果用Future的话得遍历，再get()，可能会阻塞
        for (int i = 0; i < taskNum; i++) {
            int ii = i;
            CompletableFuture.supplyAsync(() -> {
                String res = Thread.currentThread().getName();
                int c = random.nextInt(1000);
                Tools.sleepMS(c);
                res += "执行任务" + ii + "耗时" + c + "MS";
                return res;
            }, pool).whenComplete(new BiConsumer<String, Throwable>() {
                @Override
                public void accept(String r, Throwable throwable) {
                    System.out.println(r);
                }
            });
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println("主线程结束");
        pool.shutdown();
    }
}
