package love.moon.mina.demo;

/**
 * User: lovemooner
 * Date: 17-4-7
 * Time: 下午3:05
 */

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import love.moon.mina.demo.handler.MinaClientHandler;
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
        // 设置链接超时时间
        conn.setConnectTimeoutMillis(30000L);
        // 添加过滤器
        conn.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset
                        .forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));
        /** 主角登场 */
        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
        //心跳过滤器
        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory);
        heartBeat.setForwardEvent(true);//是否回发
        heartBeat.setRequestInterval(HEARTBEATRATE);//发送频率
        //connector.getSessionConfig().setKeepAlive(true);
        conn.getFilterChain().addLast("heartbeat", heartBeat);
        // 添加业务处理handler
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



}
