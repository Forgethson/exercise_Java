package src.main.java.test;

/**
 * 测试synchronized关键字
 * */
public class testSyn {
    private static final Object o1 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
//            System.out.println("t1 enter");
            synchronized (o1) {
                try {
                    System.out.println("t1 得到o1锁");
//                    Thread.sleep(3000);
                    o1.wait();
                    System.out.println("end lock t1");
                } catch (InterruptedException e) {
                    System.out.println("t1 interruptedException");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
//            System.out.println("t2 enter");
            synchronized (o1) {
                try {
                    System.out.println("t2 得到o1锁");
                    Thread.sleep(3000);
                    o1.notify();
                    System.out.println("end lock t2");
                } catch (InterruptedException e) {
                    System.out.println("t2 interruptedException");
                }
            }
        });

        Thread thread3 = new Thread(() -> {
//            System.out.println("t3 enter");
            synchronized (o1) {
                try {
                    System.out.println("t3 得到o1锁");
                    Thread.sleep(3000);
                    System.out.println("end lock t3");
                } catch (InterruptedException e) {
                    System.out.println("t3 interruptedException");
                }
            }
        });

        thread1.start(); // thread1先启动，先拿到o1的锁
        Thread.sleep(500);
        thread2.start();
        Thread.sleep(500);
        thread3.start();


//        System.out.println("主线程结束");

    }
}
