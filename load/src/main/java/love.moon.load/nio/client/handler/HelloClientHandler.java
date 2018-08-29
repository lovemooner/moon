package love.moon.load.nio.client.handler;

/**
 * Author: lovemooner
 * Date: 2018/8/26 0:12
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import love.moon.load.jload.bean.Result;
import love.moon.load.jload.monitor.IResponseLogger;
import love.moon.load.jload.monitor.ResponseLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloClientHandler extends SimpleChannelInboundHandler<String> {
    private IResponseLogger responseLogger = new ResponseLogger();
    public static Map<String, Result> resultMap = new HashMap<String, Result>(300);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        System.out.println("Client-->Receive msg:" + msg);
        Result result = resultMap.get(msg);
        if (result != null) {
            result.setRespondTime(System.currentTimeMillis());
            result.setSuccess(true);
            responseLogger.log(result);
            resultMap.remove(msg);
        }
    }

    public static final AtomicInteger count = new AtomicInteger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");

    }

//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("Client close ");
//        super.channelInactive(ctx);
//    }
//
//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("==============channel--register==============");
//    }
//
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("==============channel--unregistered==============");
//    }
}
