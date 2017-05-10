package love.celery.mina;

/**
 * User: lovemooner
 * Date: 17-4-7
 * Time: 下午3:05
 */

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static love.celery.mina.MinaServer.PORT;


public class MinaClient {
    private static final Logger LOG = LoggerFactory.getLogger(MinaServer.class);
    private static String HOST = "localhost";

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
        // 添加业务处理handler
        conn.setHandler(new MinaClientHandler());
        IoSession session = null;
        try {
            ConnectFuture future = conn.connect(new InetSocketAddress(HOST, PORT));// 创建连接
            future.awaitUninterruptibly();// 等待连接创建完成
            session = future.getSession();// 获得session
            while (true) {
                session.write("Hello World!");// 发送消息
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            LOG.error("客户端链接异常...", e);
        }

        session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
        conn.dispose();
    }


    class MinaClientHandler extends IoHandlerAdapter {
        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            cause.printStackTrace();
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {

            LOG.info("Client-> Receive Message" + message);
        }

        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
//            LOG.info("client-消息已经发送" + message);
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            LOG.info("client -session关闭连接断开");
        }

        @Override
        public void sessionCreated(IoSession session) throws Exception {
//            System.out.println("client -创建session");
        }

        @Override
        public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
//            System.out.println("client-系统空闲中...");
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
//            System.out.println("client-session打开");
        }
    }
}
