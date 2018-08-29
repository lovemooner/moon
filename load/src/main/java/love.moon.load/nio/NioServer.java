package love.moon.load.nio;

import love.moon.load.nio.server.HelloServer;
import love.moon.load.nio.server.HttpServer;
import love.moon.load.nio.server.INioServer;

/**
 * Author: lovemooner
 * Date: 2018/8/27 17:14
 */
public class NioServer {

    public static void main(String[] args) {
//        INioServer server=new HttpServer();
        INioServer server=new HelloServer();
        server.start();
    }
}
