package top.hapleow.common.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

class CuratorZookAsyncServiceImplTest {

    private static final String zkAddress = "localhost:2181";

    private static CuratorFramework client;

    @BeforeEach
    void setUp() {

        if (client != null){
            return;
        }
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(100, 3);
        client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void WatcherExist() throws Exception {

        client.checkExists().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println(event.getType());
                System.out.println(event.getState());
                System.out.println(event.getPath());
            }
        }).forPath("/persistent");

        new CountDownLatch(1).await();
    }

    @Test
    void WatcherExist1() throws Exception {

        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, new ExponentialBackoffRetry(100, 3));
        client.start();
        client.checkExists().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println(event.getType());
                System.out.println(event.getState());
                System.out.println(event.getPath());
            }
        }).forPath("/persistent");

        new CountDownLatch(1).await();
    }


    @Test
    void addZNode() throws Exception {
        client.create().withMode(CreateMode.PERSISTENT).forPath("/persistent");
    }

    @Test
    void deleteNode() throws Exception{
        client.delete().forPath("/persistent");
    }

    @Test
    void setNodeData() throws Exception{
        client.setData().forPath("/persistent","ok".getBytes());
    }
}