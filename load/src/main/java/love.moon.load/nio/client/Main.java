package love.moon.load.nio.client;

import love.moon.load.jload.bean.Config;
import love.moon.load.nio.client.INioClient;
import love.moon.load.nio.client.NIOClient100;

import static love.moon.load.jload.bean.Config.SOCKET_PORT;

/**
 * Author: lovemooner
 * Date: 2018/8/28 15:35
 */
public class Main {


    public static void main(String[] args) {
        String host = Config.URL_SLC11FSP_HOST;
        int port = SOCKET_PORT;
        INioClient client=new NIOClient100();
//        INioClient client = new NettyClient100();
        client.start(host, port);
    }
}
