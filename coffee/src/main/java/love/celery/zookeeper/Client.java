package love.celery.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/5 15:59
 */
public class Client {

    private ZooKeeper zk;

    public Client() throws IOException {
        // 创建一个与服务器的连接
         zk = new ZooKeeper(ADDRESS,50000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + "事件！");
            }
        });
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public static final String ADDRESS="slc11fsp.us.oracle.com:2181";
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        Client client=new Client();
        ZooKeeper zk=client.getZk();
        System.out.println(zk.getChildren("/dubbo/love.moon.dubbo.demo.service.DemoService",true));
//        delete
//        zk.delete("/testRootPath",-1);
        zk.close();
    }
}
