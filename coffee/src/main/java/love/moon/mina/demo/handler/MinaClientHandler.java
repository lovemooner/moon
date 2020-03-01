package love.moon.mina.demo.handler;

import love.moon.mina.demo.MinaServer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Author: lovemooner
 * Date: 2017/9/8 15:26
 */
public class MinaClientHandler extends IoHandlerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(MinaServer.class);

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        LOG.info("Client-> Receive Message:" + message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
//            LOG.info("client-消息已经发送" + message);
    }

    private static String HOST = "localhost";

    /**
     * mina 心跳包机制 只能在第一次连上，当网络中断后，或者服务器突然断电重启后，就不会自动连接了，因此需要在通道关闭的地方做重连处理：
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        LOG.info("client-> session关闭连接断开");
        int i=0;
        IoConnector connector = new NioSocketConnector();
        while(true) {
            try {
                Thread.sleep(3000);
                // 这里是异步操作 连接后立即返回
                ConnectFuture future = connector.connect(new InetSocketAddress(
                        HOST, MinaServer.PORT));
                future.awaitUninterruptibly();// 等待连接创建完成
                session = future.getSession();
                if(session.isConnected()) {
                    break;
                }else{
                    System.out.println("请检查网络!");
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("client-> 创建session");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("client-> 系统空闲中...");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("client-> session打开");
    }
}
