package src.main.java.IO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        ExecutorService pool = Executors.newFixedThreadPool(5);

        // 用主线程接收新连接线程
        while (true) {
            try {
                // (1) 阻塞方法获取新的连接
                Socket socket = serverSocket.accept();
                // (2) 每一个新的连接都创建一个线程，负责读取数据
                pool.submit(() -> {
                    try {
                        byte[] data = new byte[1024];
                        InputStream inputStream = socket.getInputStream();
                        int len;
                        // (3) 按字节流方式读取数据（如果没有数据可读，就会阻塞，或者到达流的末尾，此时分别返回-1，字符流则返回null）
                        while ((len = inputStream.read(data)) != -1) {
                            if ("pause!!!".equals(new String(data, 0, len))) {
                                System.out.println("Client本轮发送结束...阻塞等待下一轮发送");
                                continue;
                            }
                            if ("over!!!".equals(new String(data, 0, len))) {
                                System.out.println("Client发送结束，准备关闭Socket");
                                break;
                            }
                            System.out.println(new String(data, 0, len));
                        }
                        socket.close();
                        System.out.println("读取完毕，关闭Socket");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
