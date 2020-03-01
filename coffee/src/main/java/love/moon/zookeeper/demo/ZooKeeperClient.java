package love.moon.zookeeper.demo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by lovemooner on 2017/5/5.
 */

public class ZooKeeperClient {

    private static final int TIME_OUT = 30000;
    public static final String ADDRESS = "192.168.6.128:2181";

    public static void main(String[] args) throws Exception {
        String path  ="/test";
        // 监控所有被触发的事件
        ZooKeeper zookeeper = new ZooKeeper(ADDRESS, TIME_OUT, event -> System.out.println("已经触发了" + event.getType() + "事件！"));

//        System.out.println(zookeeper.getChildren("/dubbo/love.moon.dubbo.demo.service.DemoService", true));

        System.out.println("=========create 创建节点===========");
        if (zookeeper.exists(path, false) == null) {
            zookeeper.create("/test", "znode1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println(new String(zookeeper.getData("/test", false, null)));

        System.out.println("=========update 修改节点的数据==========");
        String data = "zNode2";
        zookeeper.setData("/test", data.getBytes(), -1);
        System.out.println(new String(zookeeper.getData("/test", false, null)));

        System.out.println("=======delete 删除节点==========");
        zookeeper.delete("/test", -1);

        System.out.println("节点状态：" + zookeeper.exists("/test", false));

        zookeeper.close();
    }
}