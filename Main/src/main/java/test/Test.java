package src.main.java.test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) throws UnknownHostException {
        // 获取本机 InetAddress 对象
        InetAddress localHost = Inet4Address.getLocalHost();
        System.out.println(localHost.getHostName()); // 主机名
        System.out.println(localHost.getHostAddress()); // 域名

        // 根据域名/主机名获得 InetAddress 对象
        InetAddress host = Inet4Address.getByName("www.baidu.com");
        System.out.println(host);
    }
}
