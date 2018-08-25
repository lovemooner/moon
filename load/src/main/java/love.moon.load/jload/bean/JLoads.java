package love.moon.load.jload.bean;

import love.moon.load.jload.client.IClient;
import love.moon.load.jload.monitor.IMonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2018/8/23 16:40
 */
public class JLoads {
    private IClient client;
    private static LoadCache cache = new LoadCache();
    private List<IMonitor> monitors = new ArrayList<IMonitor>();
    public static ExecutorService executor=Executors.newCachedThreadPool();


    public static LoadCache getLoadCache() {
        return cache;
    }

    public void setClient(IClient client) {
        this.client = client;
    }

    public List<IMonitor> getMonitors() {
        return monitors;
    }


    public void run() {
        for(int i=0;i<Config.threadNum;i++){
            new Thread(new Runnable() {
                public void run() {
                    client.request();
                }
            }).start();
//            executor.execute(new Runnable() {
//                public void run() {
//                    client.request();
//                }
//            });
        }

        for (IMonitor monitor : monitors) {
            monitor.start();
        }


    }

}
