package love.moon.load.jload;

import love.moon.load.jload.bean.JLoads;
import love.moon.load.jload.client.HttpClientGet101;
import love.moon.load.jload.client.IClient;
import love.moon.load.jload.client.nio.JNettyClient100;
import love.moon.load.jload.client.nio.JNioClient100;
import love.moon.load.jload.client.nio.NettyClientHandler;
import love.moon.load.jload.monitor.SummaryMonitor;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2018/8/23 13:25
 */
public class Startup {


    public static void main(String[] args) throws IOException, InterruptedException {
        JLoads loads = new JLoads();
        loads.getMonitors().add(new SummaryMonitor());
        IClient client = new HttpClientGet101();
//        IClient client = new JNioClient100();
//        IClient client = new JNettyClient100(new NettyClientHandler());
        loads.setClient(client);
        loads.run();
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
