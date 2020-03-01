package love.moon.load.nio.server.handler;

/**
 * Author: lovemooner
 * Date: 2018/8/26 0:10
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import love.moon.util.RandomUtil;

import java.net.InetAddress;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Server--> Address: "+ctx.channel().remoteAddress() + " Says : " + msg);
//        ctx.channel().writeAndFlush(RandomUtil.random() + "\r\n");
        ctx.writeAndFlush(msg+ "\r\n");
    }

    /*
     *
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
//        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
//        super.channelActive(ctx);
//    }
//
//    @Override
//    public boolean isSharable() {
//        System.out.println("==============handler-sharable==============");
//        return super.isSharable();
//    }
//
//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//        System.out.println(ctx.channel().remoteAddress() + " channel-register");
//    }
//
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//        System.out.println(ctx.channel().remoteAddress() + " channel-unregister");
//    }
//
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("==============channel-inactive==============");
//    }
}
