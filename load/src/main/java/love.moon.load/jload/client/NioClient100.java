package love.moon.load.jload.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import love.moon.load.jload.bean.Config;
import love.moon.load.jload.bean.Result;
import love.moon.load.jload.monitor.IResponseLogger;
import love.moon.load.jload.monitor.ResponseLogger;
import love.moon.load.nio.HelloClientInitializer;
import love.moon.util.RandomUtil;

/**
 * Author: lovemooner
 * Date: 2018/8/26 1:38
 */
public class NioClient100 implements IClient {
    public static String host = Config.URL_SLC11FSP_HOST;
    public static int port = 7878;
    private IResponseLogger responseLogger = new ResponseLogger();

    public void request() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new HelloClientInitializer());
        Channel ch = null;
        // 连接服务端
        try {
            ch = b.connect(host, port).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        while (true) {
            ch.writeAndFlush(RandomUtil.random() + "\r\n");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            result.setRespondTime(System.currentTimeMillis());
            result.setSuccess(true);
            responseLogger.log(result);
        }

    }
}
