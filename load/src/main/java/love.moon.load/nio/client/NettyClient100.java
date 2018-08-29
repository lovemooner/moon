package love.moon.load.nio.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import love.moon.load.jload.bean.Config;
import love.moon.load.nio.client.handler.HelloClientHandler;
import love.moon.load.nio.client.handler.HelloClientInitializer;
import love.moon.util.RandomUtil;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2018/8/26 0:43
 */
public class NettyClient100 implements INioClient{


    public void start(String host,int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HelloClientInitializer());
            Channel ch = b.connect(host, port).sync().channel();
            while (true) {
                ch.writeAndFlush(HelloClientHandler.count.incrementAndGet() + "\r\n");
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}
