package love.moon.mina.simple;

/**
 * User: lovemooner
 * Date: 17-4-7
 * Time: 下午3:05
 */

import love.moon.mina.demo.KeepAliveMessageFactoryImpl;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MinaClient {
    private static final Logger LOG = LoggerFactory.getLogger(MinaServer.class);
    private static String HOST = "localhost";
    //  30秒后超时
    private static final int IDELTIMEOUT = 30;
    //   15秒发送一次心跳包
    private static final int HEARTBEATRATE = 15;

    public static void main(String[] args) {
        MinaClient client = new MinaClient();
        client.connect();

    }

    public void connect() {
        IoConnector conn = new NioSocketConnector();
        conn.setConnectTimeoutMillis(30000L);
        conn.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset
                        .forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));
        conn.setHandler(new MinaClientHandler());
        IoSession session = null;
        try {
            ConnectFuture future = conn.connect(new InetSocketAddress(HOST, MinaServer.PORT));// 创建连接
            future.awaitUninterruptibly();// 等待连接创建完成
            session = future.getSession();// 获得session
            while (true) {
                session.write("Hello World!");// 发送消息
                Thread.sleep(30000);
            }
        } catch (Exception e) {
            LOG.error("客户端链接异常...", e);
        }

        session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
        conn.dispose();
    }


    class MinaClientHandler extends IoHandlerAdapter {
        @Override
        public void exceptionCaught(IoSession session, Throwable cause) {
            cause.printStackTrace();
        }

        @Override
        public void messageReceived(IoSession session, Object message) {
            LOG.info("Client-> Receive Message:" + message);
        }

        @Override
        public void messageSent(IoSession session, Object message) {
//            LOG.info("client-消息已经发送" + message);
        }

        @Override
        public void sessionClosed(IoSession session) {
            LOG.info("client-> session关闭连接断开");
        }

        @Override
        public void sessionCreated(IoSession session) {
            System.out.println("client-> 创建session");
        }

        @Override
        public void sessionIdle(IoSession session, IdleStatus status) {
            System.out.println("client-> 系统空闲中...");
        }

        @Override
        public void sessionOpened(IoSession session) {
            System.out.println("client-> session打开");
        }
    }
}
