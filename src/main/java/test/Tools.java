package test;

import java.util.concurrent.TimeUnit;

public class Tools {
    public static void sleepMS(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
