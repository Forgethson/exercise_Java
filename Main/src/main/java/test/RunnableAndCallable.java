package test;

/**
 * RunnableAndCallable 测试
 */

import java.util.concurrent.*;

public class RunnableAndCallable {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        testUse(pool);
//        testException(pool);
        pool.shutdown();
    }

    // 基本使用
    public static void testUse(ExecutorService pool) throws Exception {
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
    }

    // 测试是否支持异常
    public static void testException(ExecutorService pool) throws Exception {
        // 查看FutureTask源码最终集继承是 Runnable + Future<V>接口，所以可通过Executor(线程池) 来执行,也可传递给Thread对象执行。
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws RuntimeException {
                int a = 100 / 0;
                return String.valueOf(a);
            }
        });

        // 执行Runnable实现类，不能catch异常
        pool.execute(() -> {
            int a;
            try {
                a = 100 / 0;
                System.out.println("RunnableThread正常执行，a = " + a);
            } catch (Exception e) {
                System.err.println("RunnableThread出异常了");
                e.printStackTrace();
            }
        });

        // 执行FutureTask，如果出现异常会带到get里面。。
        pool.submit(futureTask);
        String s;
        try {
            // 调用get方法的时候，支持捕获异常
            s = futureTask.get();
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("已捕获 CallableThread 的异常：" + e.toString());
        }
        TimeUnit.SECONDS.sleep(3);
    }
}

