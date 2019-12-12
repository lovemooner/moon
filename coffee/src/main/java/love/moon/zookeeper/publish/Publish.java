package love.moon.zookeeper.publish;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static love.moon.zookeeper.demo.ZooKeeperClient.ADDRESS;

/**
 * Created by lovemooner on 2017/5/6.
 */
public class Publish implements Watcher{
    private static CountDownLatch latch =  new CountDownLatch(1);
    private static Stat stat = new Stat();
    private static ZooKeeper zk =null;
    private final static Integer  SESSION_TIMEOUT = 50000;

    public static void main(String[] args) {
        try {
            String path  ="/publish";
            zk =  new ZooKeeper(ADDRESS,SESSION_TIMEOUT,new Publish());
            zk.delete(path,-1);
            if (zk.exists(path, false) == null) {
                zk.create(path, "Hi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            latch.await();
            System.out.println("zk connection");
            byte[]  temp = zk.getData(path,true,stat);
            System.out.println("init data :pulish node data "+new String(temp));
            int i=0;
            while(true){
                System.out.println( "publish new Data:"+i);
                zk.setData(path,String.valueOf(i).getBytes(),-1);
                Thread.sleep(5000L);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void process(WatchedEvent event) {
        if(Event.KeeperState.SyncConnected == event.getState()){
            System.out.println("receive watched event:"+event);
            System.out.println(event.getState());
            latch.countDown();
        }
    }
}

