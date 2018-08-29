package love.moon.load.nio.server;

/**
 * Author: lovemooner
 * Date: 2018/8/26 0:08
 */


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import love.moon.load.jload.bean.Config;
import love.moon.load.nio.server.handler.HelloServerInitializer;

public class HelloServer implements INioServer{

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new HelloServerInitializer());
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = bootstrap.bind(Config.NIO_HTTP_PORT).sync();
            System.out.println("Server start,at port:"+Config.NIO_HTTP_PORT);
            System.out.println(f.channel());
            Channel ch= f.channel().closeFuture().sync().channel();
            // 可以简写为
            /* b.bind(portNumber).sync().channel().closeFuture().sync(); */
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
