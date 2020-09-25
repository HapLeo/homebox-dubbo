package top.hapleow.common.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
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

        if (client != null) {
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
    void deleteNode(String path) throws Exception {
        client.delete().forPath(path);
        System.out.println(path + " is deleted.");
    }

    @Test
    void setNodeData(String path, String data) throws Exception {
        client.setData().forPath(path, data.getBytes());
        System.out.println("data is setted.");
    }

    /**
     * Listener监听指定节点的新增、修改、删除
     */
    @Test
    void NodeCacheListener() throws Exception {

        // 设置监听
        NodeCache nodeCache = new NodeCache(client, "/nodecache");
        nodeCache.start();
        nodeCache.getListenable().addListener(() -> System.out.println("nodecache is changed."));

        checkExistWatcher("/nodecache");
        // 新增节点
        Thread.sleep(3000);
        client.create().withMode(CreateMode.PERSISTENT).forPath("/nodecache");
        System.out.println("nodecache is created by manual.");

        // 修改节点（修改值，节点名不能修改）
        Thread.sleep(3000);
        client.setData().forPath("/nodecache", "abc".getBytes());
        System.out.println("nodecache is setData by manual.");

        // 查询
        Thread.sleep(3000);
        client.getData().forPath("/nodecache");
        System.out.println("nodecache execute getData method.");

        // 删除节点
        Thread.sleep(3000);
        client.delete().forPath("/nodecache");
        System.out.println("nodecache is deleted by manual.");
    }

    /**
     * Watcher监听节点的新增、删除、修改，只触发一次
     *
     * @param path
     * @throws Exception
     */
    @Test
    public void checkExistWatcher(String path) throws Exception {

        client.checkExists().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println("checkExistWatcher invoked:path=" + event.getPath()
                        + ",type=" + event.getType() + ",state=" + event.getState());
            }
        }).forPath(path);
    }

    /**
     * Watcher监听修改动作，只触发一次
     *
     * @param path
     * @throws Exception
     */
    @Test
    public void getDataWatcher(String path) throws Exception {
        // Watcher来监听数据的变化，只触发一次，对节点的增删查不会触发，只在修改节点值的时候触发一次
        client.getData().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println("getDataWatcher invoked:path="+event.getPath()+",type=" + event.getType() + ",state=" + event.getState());
            }
        }).forPath(path);
    }

    /**
     * 监听指定节点的子节点的变更
     */
    @Test
    void PathCacheListener() throws Exception {

        PathChildrenCache nodeCache = new PathChildrenCache(client, "/pathcache2/test2/ok2", true);
        nodeCache.start();
        nodeCache.getListenable().addListener((client, event) -> System.out.println("/pathcache2/test2/ok2 is changed."));

        Thread.sleep(5000);
        deleteNode("/pathcache2/test2/ok2");
        System.out.println("delete invoked.");
    }
}