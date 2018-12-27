package com.sec.demo.plugs;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class ZookeeperDemo {

    private static final String CCONNECTION_STRING = "10.84.3.20:2181";
    private static final int SESSION_TIMEOUT = 5000;

    private static CountDownLatch latch = new CountDownLatch(1);

    @PostConstruct
    public void testZookeeper() throws Exception{
        //connect zookeeper
        ZooKeeper zk = new ZooKeeper(CCONNECTION_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                    latch.countDown();
                }
            }
        });
        latch.await();
        //获取zookeeper对象
//        System.out.println(zk);
        zk.getChildren("/", null, new AsyncCallback.Children2Callback() {
            @Override
            public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
                for (String node:list){
                    System.out.println(node);
                }
            }
        },null);

        //判断节点是否存在
        zk.exists("/", null, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int i, String s, Object o, Stat stat) {
                if(stat != null){
                    System.out.println("node exists");
                }else{
                    System.out.println("node does not exists");
                }
            }
        },null);

        //获取某节点数据
        zk.getData("/zookeeper", null, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println(new String(bytes));
            }
        },null);
    }

}
