package love.moon.netty.demo;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2017/5/10 11:29
 */
public class HelloClient {

    public static void main(String args[]) {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(() -> Channels.pipeline(new HelloClientHandler()));
        // 连接到本地的8000端口的服务端
        bootstrap.connect(new InetSocketAddress(
                "127.0.0.1", 8000));
    }

    private static class HelloClientHandler extends SimpleChannelHandler {



        @Override
        public void channelConnected(ChannelHandlerContext ctx,
                                     ChannelStateEvent e) {
            System.out.println("channelConnected");
        }
    }
}