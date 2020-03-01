package love.moon.mina.demo;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * Author: lovemooner
 * Date: 2017/9/8 15:15
 */
 public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getRequest
     * (org.apache.mina.core.session.IoSession)
     */
    @Override
    public Object getRequest(IoSession session) {
//            i++;
//            Log.d("session",i+"");
//          if (i > 5) {
//              try {
//                    Log.d("session","睡眠");
//                  Thread.sleep(40000);
//              } catch (InterruptedException e) {
//                  // TODO Auto-generated catch block
//                  e.printStackTrace();
//              }
//          }
        return "heartbeatrequest";
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getResponse
     * (org.apache.mina.core.session.IoSession, java.lang.Object)
     */
    @Override
    public Object getResponse(IoSession session, Object request) {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isRequest
     * (org.apache.mina.core.session.IoSession, java.lang.Object)
     */
    @Override
    public boolean isRequest(IoSession session, Object message) {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isResponse
     * (org.apache.mina.core.session.IoSession, java.lang.Object)
     */
    @Override
    public boolean isResponse(IoSession session, Object message) {
//        if (message instanceof HEARTBEATRESPONSE) {
//            System.out.println("session是响应心跳包");
//            return true;
//        }
        return false;
    }

}


