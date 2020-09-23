package top.hapleow.common.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用zookeeper的客户端Curator操作zookeeper的示例
 * 此处均为异步非阻塞方法
 */
public class CuratorZookAsyncServiceImpl implements IZookService {

    private static final String zkAddress = "localhost:2181";

    /**
     * 启动客户端，连接zookeeper服务器
     */
    public static CuratorFramework start() {

        // 失败重试策略：重试三次，时间间隔指数级增加
        RetryPolicy retry = new ExponentialBackoffRetry(1000, 3);
        // 创建客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retry);
        client.start();

        client.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {

                System.out.println(event.getType() + " node " + event.getName());
            }
        });
        return client;
    }

    public static void main(String[] args) throws Exception {

        CuratorFramework client = start();

        // 创建持久节点
        client.create().withMode(CreateMode.PERSISTENT).inBackground().forPath("/persistent", "created a persistent node.".getBytes());

        // 创建临时节点(断开连接后该节点会被删除)
        client.create().withMode(CreateMode.EPHEMERAL).inBackground().forPath("/ephemeral", "created an ephemeral node.".getBytes());

        // 创建持久顺序节点（zookeeper自动分配递增后缀）
        for (int i = 0; i < 5; i++) {
            client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).inBackground().forPath("/persistent_sequential");
        }

        // 创建临时顺序节点（zookeeper自动分配递增后缀）
        for (int i = 0; i < 5; i++) {
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).inBackground().forPath("/persistent_sequential");
        }

        // 检查节点是否存在,为null则不存在
        Stat stat = client.checkExists().inBackground().forPath("/persistent");
        System.out.println("节点是否存在？" + stat != null);

        // 获取节点数据,如果指定的节点不存在则会报错没有该节点
        byte[] persistentData = client.getData().inBackground().forPath("/persistent");
        System.out.println("persistent节点的数据为：" + new String(persistentData));

        // 设置节点数据
        client.setData().forPath("/persistent","updated persistent node value.".getBytes());
        byte[] bytes = client.getData().inBackground().forPath("/persistent");
        System.out.println("设置节点数据：" + new String(bytes));


        // 查询所有子节点(不包括子节点的子节点)
        List<String> children = client.getChildren().inBackground().forPath("/");
        System.out.println("根节点的所有子节点：" + children);

        // 删除节点(如果有子节点则报错提示该节点非空)
         client.delete().inBackground(new BackgroundCallback() {
             @Override
             public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                 System.out.println("processResult:" + event.getName() +"-"+ event.getType() +"-"+ event.getPath());
             }
         }).forPath("/persistent");
//
        // 删除节点，如果存在子节点则级联删除
        client.delete().deletingChildrenIfNeeded().inBackground().forPath("/ephemeral");

        new CountDownLatch(1).await();


    }
}
