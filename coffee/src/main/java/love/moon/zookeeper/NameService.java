package love.moon.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static love.moon.zookeeper.demo.ZooKeeperClient.ADDRESS;


/**
 * Author: lovemooner
 * Date: 2017/5/5 16:27
 */
public class NameService {
    private ZooKeeper zk = null; // ZooKeeper对象
    private static String nameroot = "/NameService";
    private String namerootvalue = "IsNameService";
    private String namevalue = "IsName";


    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        NameService service = new NameService(ADDRESS);
        System.out.println(service.ReadAll());
    }

    /**
     * @函数：命名服务构造函数
     * @参数：zk的地址端口 描述：初始化zk实例，创建命名服务根路径
     */
    public NameService(String url) throws InterruptedException, IOException, KeeperException {
        // 初始化，如果当前有alive的zk连接则先关闭
        if (zk != null && zk.getState().isAlive() == true)
            zk.close();
        zk = new ZooKeeper(url, 50000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + "事件！");
            }
        }); // 重新建立连接
        System.out.println("zookeeper connect success:url=" + url);
        // 判断是否有/NameService，如果没有，则创建该路径，用来作为所有的集中配置信息的根目录
        if (zk.exists(nameroot, false) == null) {
            zk.create(nameroot, namerootvalue.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(nameroot + " create success!");
        }

    }

    /**
     * @函数： 注销zk实例
     */
    public void UnNaming() throws InterruptedException {
        if (zk != null) {
            zk.close();
            System.out.println("zookeeper close success!");
            zk = null;
        }
    }

    /**
     * @函数：注册一个全局名字
     * @描述：待注册的名字字符串name，在zk中创建一个/NameService/name的znode路径
     * @参数： 待注册的名字字符串name
     * @返回值： 0 表示注册成功 -1 表示出错 1 表示该命名已被注册
     */
    @SuppressWarnings("finally")
    public int Registered(String name) {
        String path = nameroot + "/" + name;
        int ret = 0;
        try {
            if (zk.exists(path, false) == null) {
                zk.create(path, namevalue.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                System.out.println(name + " registered success!");
            } else {
                ret = 1;
                System.out.println(name + " is exists, can not regist again!");
            }
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            ret = -1;
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            ret = -1;
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            return ret;
        }
    }

    /**
     * @函数：注销一个全局名字
     * @描述：待注销的名字字符串name，在zk中删除/NameService/name的znode路径
     * @参数： 待注销的名字字符串name
     * @返回值： 0 表示注销成功 -1 表示出错 1 表示该命名未注册，不存在命名服务系统中
     */
    @SuppressWarnings("finally")
    public void canceled(String name) throws KeeperException, InterruptedException {
        String path = nameroot + "/" + name;
        if (zk.exists(path, false) != null) {
            zk.delete(path, -1);
            System.out.println(name + " canceled success!");
        }
    }

    /**
     * @函数：获取命名服务系统的所有命名
     * @描述：
     * @参数：
     * @返回值：命名列表
     */
    public List<String> ReadAll() throws KeeperException, InterruptedException {
        List<String> list = new ArrayList<String>();
        list = zk.getChildren(nameroot, false);
        return list;
    }
}
