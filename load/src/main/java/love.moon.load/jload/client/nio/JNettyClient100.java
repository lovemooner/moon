package love.moon.load.jload.client.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import love.moon.load.jload.bean.Config;
import love.moon.load.jload.bean.Result;
import love.moon.load.jload.client.IClient;

import java.util.concurrent.atomic.AtomicInteger;

import static love.moon.load.jload.client.nio.NettyClientHandler.resultMap;

/**
 * Author: lovemooner
 * Date: 2018/8/26 1:38
 */
public class JNettyClient100 implements IClient {

    private ChannelInboundHandler handler;
    public static final AtomicInteger count = new AtomicInteger();

    public JNettyClient100(ChannelInboundHandler handler){
        this.handler=handler;
    }

    public void request() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer() {
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast("handler", handler);
                        }
                    });
            Channel channel=bootstrap.connect(Config.URL_SLC11FSP_HOST, Config.NIO_HTTP_PORT).sync().channel();
            while (true) {
                Result result=new Result();
                String result_id=String.valueOf(count.incrementAndGet());
                resultMap.put(result_id,result);
                result.setRequestTime(System.currentTimeMillis());
                channel.writeAndFlush(result_id + "\r\n");
                Thread.sleep(1000l);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }


    }
}
