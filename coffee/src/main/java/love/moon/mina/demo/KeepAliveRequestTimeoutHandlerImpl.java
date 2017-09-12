package love.moon.mina.demo;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

/**
 * Author: lovemooner
 * Date: 2017/9/8 15:19
 * @ClassName: KeepAliveRequestTimeoutHandlerImpl
 * @Description: 当心跳超时时的处理，也可以用默认处理 这里like
 */
public class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler {

    /*
     * (non-Javadoc)
     *
     * @seeorg.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler#
     * keepAliveRequestTimedOut
     * (org.apache.mina.filter.keepalive.KeepAliveFilter,
     * org.apache.mina.core.session.IoSession)
     */
    @Override
    public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {
        System.out.println("心跳超时！");
    }

}