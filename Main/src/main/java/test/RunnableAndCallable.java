package src.main.java.test;

import java.util.concurrent.*;

public class RunnableAndCallable {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        // 查看FutureTask源码最终集继承是 Runnable + Future<V>接口，所以可通过Executor(线程池) 来执行,也可传递给Thread对象执行。
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws RuntimeException {
                int a = 100 / 0;
                return String.valueOf(a);
            }
        });

        // 执行Runnable实现类
        pool.execute(()->{
            int a;
            try {
                a = 100 / 1;
                System.out.println("RunnableThread正常执行，a = " + a);
            } catch (Exception e) {
                System.err.println("RunnableThread出异常了");
                e.printStackTrace();
            }
        });

        // 执行FutureTask
        pool.submit(futureTask);
        String s;
        try {
            // 调用get方法的时候，支持捕获异常
            s = futureTask.get();
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("捕获 CallableThread 的异常");
        }

        TimeUnit.SECONDS.sleep(3);
        pool.shutdown();
    }
}

