package src.main.java.IO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IOClient {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        pool.submit(new ClientTask("Client-1", 8000));
        TimeUnit.HOURS.sleep(1);
        pool.shutdown();
    }
}

class ClientTask implements Runnable {
    private final String name;
    private final int port;

    public ClientTask(String name, int port) {
        this.name = name;
        this.port = port;
    }

    @Override
    public void run() {
        // tyy-with-resource 自动关闭Socket相关资源
        try (Socket socket = new Socket("127.0.0.1", port)) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("ClientSocket: " + name + "已连接").getBytes());
            outputStream.flush();
            int times = 2;
            while (times > 0) {
                try {
                    times--;
                    outputStream.write((new Date() + " " + name + ": hello world").getBytes());
                    outputStream.flush();
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 双方约定好的发送暂停标记
            outputStream.write(("pause!!!").getBytes());
            outputStream.flush();
            TimeUnit.SECONDS.sleep(3);

            times = 2;
            while (times > 0) {
                try {
                    times--;
                    outputStream.write((new Date() + " " + name + ": how are you").getBytes());
                    outputStream.flush();
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 双方约定好的发送结束标记
            outputStream.write(("over!!!").getBytes());
            outputStream.flush();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭 ClientSocket 连接");
        }
    }
}