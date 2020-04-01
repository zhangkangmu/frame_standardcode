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
 * @Company 深圳黑马程序员
 * @Date 2020/3/26 9:29
 * @Version V1.0
 */
public class TestCurator {

    // 3.3.3、创建节点
    @Test
    public void createNode() throws Exception {

        /**
         * ExponentialBackoffRetry：重试策略
         *   int baseSleepTimeMs, int maxRetries
         *   参数1：每隔多长时间尝试连接（单位是毫秒）
         *   参数2：最大重试次数
         *   参数3（可以省略）：最大sleep时间，如果上述的当前sleep计算出来比这个大，那么sleep用这个时间
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        /**
         * CuratorFrameworkFactory.newClient：创建客户端连接对象
         *   * 参数1：连接服务器的IP地址和Zookeeper的端口号（默认是2181）
         *   * 参数2：session会话的超时时间（单位毫秒）
         *   * 参数3：连接的超时时间（单位毫秒）
         *   * 参数4：重试策略
         */
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",1000,1000,retryPolicy);
        // 开启客户端连接对象
        client.start();

        //1. 创建一个空节点(a)（只能创建一层节点）
        // client.create().forPath("/a");
        //2. 创建一个有内容的b节点（只能创建一层节点）
        // client.create().forPath("/b","bbb".getBytes());
        //3. 创建持久节点，同时创建多层节点
        // client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/c/c_1","ccc".getBytes());
        //4. 创建带有的序号的持久节点
        // client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/e","eee".getBytes());
        //5. 创建临时节点（客户端关闭，节点消失），设置延时5秒关闭（Thread.sleep(5000)）
        // client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/f","fff".getBytes());
        //6. 创建临时带序号节点（客户端关闭，节点消失），设置延时5秒关闭（Thread.sleep(5000)）
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/f","fff".getBytes());
        // 设置延时5秒关闭（Thread.sleep(5000)）
        Thread.sleep(5000);
        // System.in.read(); // 线程阻塞，防止junit方法执行完成
        // 关闭客户端连接对象（临时节点消失）
        client.close();
    }

    // 3.3.4、修改节点数据
    @Test
    public void updateNodeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
        // 开启客户端连接对象
        client.start();
        // client.setData().forPath("/a","abcd".getBytes());
        client.setData().forPath("/c/c_1","efg".getBytes());
        // 关闭客户端连接对象
        client.close();
    }

    // 3.3.5、节点数据查询
    @Test
    public void getNodeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
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
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
        // 开启客户端连接对象
        client.start();
        // 删除没有子节点的节点
        // client.delete().forPath("/a");
        // 删除带有子节点的节点（先删除子节点、再删除父节点）
//        client.delete().forPath("/c/c_1");
//        client.delete().forPath("/c/c_2");
//        client.delete().forPath("/c");
        // 递归删除，不管是否有子节点，父节点和子节点全部删除
        // client.delete().deletingChildrenIfNeeded().forPath("/c");
        // 强制保证删除一个节点（了解）
        // 只要客户端会话有效，那么Curator会在后台持续进行删除操作，直到节点删除成功。
        // 比如遇到一些网络异常的情况，此guaranteed的强制删除就会很有效果。
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/c");
        // 关闭客户端连接对象
        client.close();
    }
}
