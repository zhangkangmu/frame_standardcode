package hong;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

/**
 * @ClassName TestCurator
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/26 9:29
 * @Version V1.0
 */
public class TestCuratorCluster {

    // 3.3.3、创建节点（集群：关闭防火墙）
    @Test
    public void createNode() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.175.134:2181",1000,1000,retryPolicy);
        // 开启客户端连接对象
        client.start();

        //3. 创建持久节点，同时创建多层节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/c/c_1","ccc".getBytes());

        // 关闭客户端连接对象（临时节点消失）
        client.close();
    }

    // 3.3.4、修改节点数据（集群）
    @Test
    public void updateNodeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.175.130:2181",retryPolicy);
        // 开启客户端连接对象
        client.start();
        client.setData().forPath("/c/c_1","abcdefg".getBytes());
        // 关闭客户端连接对象
        client.close();
    }

    // 3.3.5、节点数据查询（集群）
    @Test
    public void getNodeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.175.134:2181",retryPolicy);
        // 开启客户端连接对象
        client.start();
        byte[] bytes = client.getData().forPath("/c/c_1");
        System.out.println(new String(bytes));
        // 关闭客户端连接对象
        client.close();
    }

    // 3.3.6、删除节点
    @Test
    public void deleteNodeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.175.134:2181",retryPolicy);
        // 开启客户端连接对象
        client.start();

        client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/c");
        // 关闭客户端连接对象
        client.close();
    }
}
