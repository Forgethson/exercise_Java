package test.zkTest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZKTest {
    ZKService zkService;

    @Before
    public void init() {
        zkService = new ZKService();
    }

    @Test
    public void testGetNode() throws Exception {
        String path = "/groupA/node1";
        byte[] nodeData = zkService.getNodeData(path);
        System.out.println(StringUtils.join("节点：【", path, "】，数据：", new String(nodeData)));
    }

    @Test
    public void testCreateNode() throws Exception {
        String data = "Hello world!";
        zkService.createNode("/groupA/node1", data);
        zkService.createNode("/groupA/node2", data);
        zkService.createNode("/groupA/node3", data);
    }

    @Test
    public void testGetNodes() throws Exception {
        String path = "/groupA";
        List<String> nodes = zkService.getChildNodes(path);
        nodes.forEach(System.out::println);
    }

    @Test
    public void testWatch() throws Exception {
        String path = "/watch";
        zkService.watchNode(path);
        TimeUnit.SECONDS.sleep(1);
        zkService.setData(path, "11111");
        TimeUnit.SECONDS.sleep(1);
        zkService.setData(path, "22222");
        TimeUnit.SECONDS.sleep(1);
        zkService.setData(path, "33333");
        TimeUnit.SECONDS.sleep(10);
    }
}
