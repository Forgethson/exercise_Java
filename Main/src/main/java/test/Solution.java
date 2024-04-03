package test;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;

class Solution {
    static boolean run = true;    //添加volatile
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while(run){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("运行中...");
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(3);
        run = false; // 如果不加volatile，线程t不会如预想的停下来
    }
}

