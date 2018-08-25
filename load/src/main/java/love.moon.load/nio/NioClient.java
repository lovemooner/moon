package love.moon.load.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import love.moon.load.jload.bean.Config;
import love.moon.load.jload.bean.Result;
import love.moon.load.nio.HelloClientInitializer;
import love.moon.util.RandomUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: lovemooner
 * Date: 2018/8/26 0:43
 */
public class NioClient {

    public static String host = Config.URL_SLC11FSP_HOST;
    public static int port = 7878;

    /**
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HelloClientInitializer());

            // 连接服务端
            Channel ch = b.connect(host, port).sync().channel();

            for (;;) {
                ch.writeAndFlush(RandomUtil.random() + "\r\n");
                Thread.sleep(1000L);
            }
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}
