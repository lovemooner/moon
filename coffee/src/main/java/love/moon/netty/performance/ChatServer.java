package love.moon.netty.performance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: lovemooner
 * Date: 2017/5/26 11:06
 */
public class ChatServer {
    private static final Logger LOG = LoggerFactory.getLogger(ChatServer.class);
    private static Random random = new Random();
    private static int max = 0;
    private final static Map<Integer, Channel> channels = new HashMap<Integer, Channel>();

    public static void main(String[] args) throws Exception {
        if(args.length <1){
            args = new String[]{"8877","true"};
        }
        ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        ServerBootstrap bootstrap = new ServerBootstrap(factory);

        ChatServerHandler handler = new ChatServerHandler();
        ChannelPipeline pipeline = bootstrap.getPipeline();
        pipeline.addLast("chat", handler);

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(Integer.valueOf(args[0])));

        boolean fillChat = "true".equals(args[1]);
        if (fillChat) {
            ChannelManagerThread cmt = new ChannelManagerThread();
            cmt.start();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String command = br.readLine();
            if ("dump".equals(command)) {
                System.out.println("当前活着的数量:" + channels.size());
            } else if ("help".equals(command)) {
                System.out.println("命令列表:");
                System.out.println("dump:打印当前情况");
                System.out.println("help:帮助文档");
            }
        }

    }

    static class ChannelManagerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    if(max < channels.size()){
                        max = channels.size() ;
                        System.out.println("live:"+channels.size());
                    }

                    for (Channel channel : channels.values()) {
                        if (random.nextInt(100)>70) {
                            ChannelBuffer buffer = new DynamicChannelBuffer(256);
                            buffer.writeBytes("Hey!有人来找你了!".getBytes());
                            channel.write(buffer);
                        }
                    }
                    sleep(500);
                } catch (InterruptedException e) {

                }
            }
        }
    }


    @Sharable
    static class ChatServerHandler extends SimpleChannelHandler {
        @Override
        public void channelConnected(ChannelHandlerContext ctx,
                                     ChannelStateEvent e) {
            Channel ch = e.getChannel();
            ChannelBuffer cb = new DynamicChannelBuffer(256);
            cb.writeBytes("Hell!你来了啊!".getBytes());
            ch.write(cb);
            channels.put(e.getChannel().getId(), e.getChannel());
        }


        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
            e.getCause().printStackTrace();
            channels.remove(e.getChannel().getId());
            LOG.warn("remove channel by exception! id:" + e.getChannel().getId());

            e.getChannel().close();
        }

        @Override
        public void channelDisconnected(ChannelHandlerContext ctx,
                                        ChannelStateEvent e) throws Exception {
            channels.remove(e.getChannel().getId());
            LOG.warn("remove channel by exception! id:" + e.getChannel().getId());

        }
    }
}
