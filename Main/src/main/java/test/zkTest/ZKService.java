package test.zkTest;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.function.Consumer;

public class ZKService {
    // curator 提供的zookeeper客户端
    private CuratorFramework client;
    // zookeeper根路径节点
    private static final String ROOT_PATH = "zkTest";
    private static final String ZK_ADDRESS = "127.0.0.1:2181";

    public ZKService() {
        // 指数时间重试
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        // zookeeper的地址固定，不管是服务提供者还是，消费者都要与之建立连接
        // sessionTimeoutMs 与 zoo.cfg中的tickTime 有关系，
        // zk还会根据minSessionTimeout与maxSessionTimeout两个参数重新调整最后的超时值。默认分别为tickTime 的2倍和20倍
        // 使用心跳监听状态
        client = CuratorFrameworkFactory.builder().connectString(ZK_ADDRESS).sessionTimeoutMs(10000).connectionTimeoutMs(10000).retryPolicy(policy).namespace(ROOT_PATH).build();
        client.start();
        System.out.println("zookeeper 连接成功" + client);
    }

    public void createNode(String nodePath, String data) throws Exception {
        if (StringUtils.isEmpty(nodePath)) {
            System.out.println("节点【" + nodePath + "】不能为空");
            return;
        }

        // 对节点是否存在进行判断，否则会报错：【NodeExistsException: KeeperErrorCode = NodeExists for /root】
        Stat exists = client.checkExists().forPath(nodePath);
        if (null != exists) {
            System.out.println("节点【" + nodePath + "】已存在，不能新增");
            return;
        } else {
            System.out.println(StringUtils.join("节点【", nodePath, "】不存在，可以新增节点！"));
        }

        // 创建节点，并为当前节点赋值内容
        if (StringUtils.isNotBlank(data)) {
            // 创建临时节点，并为当前节点赋值内容
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(nodePath, data.getBytes());
        }
    }

    public byte[] getNodeData(String nodePath) throws Exception {
        // 获取某个节点数据
        return client.getData().forPath(nodePath);
    }

    public List<String> getChildNodes(String nodePath) throws Exception {
        //获取某个节点的所有子节点
        return client.getChildren().forPath(nodePath);
    }

    public void setData(String nodePath, String data) throws Exception {
        client.setData().forPath(nodePath, data.getBytes());
    }

    public void watchNode(String nodePath) throws Exception {
        Stat exists = client.checkExists().forPath(nodePath);
        if (null == exists) {
            String data = "watch! watch! watch!";
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(nodePath, data.getBytes());
        }

        NodeCache nodeCache = new NodeCache(client, nodePath, false);
        NodeCacheListener l = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData childData = nodeCache.getCurrentData();
                System.out.printf("ZNode节点状态改变, path=%s\n", childData.getPath());
                System.out.printf("ZNode节点状态改变, data=%s\n", new String(childData.getData(), "Utf-8"));
                System.out.printf("ZNode节点状态改变, stat=%s\n", childData.getStat());
            }
        };
        nodeCache.getListenable().addListener(l);
        nodeCache.start();
    }
}
