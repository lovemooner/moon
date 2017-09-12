package love.moon.mina.demo.handler;

import love.moon.mina.demo.MinaServer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Author: lovemooner
 * Date: 2017/9/8 15:28
 */
public class MinaServerHandler extends IoHandlerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(MinaServerHandler.class);
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
