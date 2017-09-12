package love.moon.mina.simple;

/**
 * User: lovemooner
 * Date: 17-4-7
 * Time: 下午2:59
 */

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;

public class MinaServer {
    private static final Logger LOG = LoggerFactory.getLogger(MinaServer.class);
    private int count=5;

    public static final int PORT = 8888;

    public static void main(String[] args) throws Exception {
        MinaServer server=new MinaServer();
        server.start();
    }


    public void start() throws IOException {
        // 创建一个非阻塞的server端的Socket，因为这里是服务端所以用IoAcceptor
        IoAcceptor acceptor = new NioSocketAcceptor();
        // 添加一个日志过滤器
//        acceptor.getFilterChain().addLast("logger", new IoFilterAdapter());
        // 添加一个编码过滤器
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));

        // 绑定业务处理器,这段代码要在acceptor.bind()方法之前执行，因为绑定套接字之后就不能再做这些准备
        acceptor.setHandler(new MinaServerHandler());
        // 设置读取数据的缓冲区大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        // 绑定一个监听端口
        acceptor.bind(new InetSocketAddress(PORT));
    }


    class MinaServerHandler extends IoHandlerAdapter {
        @Override
        public void exceptionCaught(IoSession session, Throwable cause)throws Exception {
            cause.printStackTrace();
        }

        @Override
        public void messageReceived(IoSession session, Object message)throws Exception {
//            if(count--<0){
//                throw new NullPointerException();
//            }
            String str = message.toString();
            LOG.info("server-> receive message: "+message);
            if( str.trim().equalsIgnoreCase("quit") ) {
                session.close(Boolean.TRUE);
                return;
            }
            Date date = new Date();
            session.write( "Hi Client"+ date.toString() );
        }

        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            LOG.info("server ->消息已经发出");
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            LOG.info("server ->server-session关闭连接断开");
        }

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            LOG.info("server ->server-session创建，建立连接");
        }

        @Override
        public void sessionIdle(IoSession session, IdleStatus status)throws Exception {
            LOG.info("server ->server-服务端进入空闲状态..");
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            LOG.info("server->服务端与客户端连接打开...");
        }
    }
}