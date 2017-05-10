package love.celery.mina.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Author: lovemooner
 * Date: 2017/5/10 16:27
 */
public class SocketAcceptor extends AbstractAcceptor {



    public SocketAcceptor() throws IOException {
        super();
    }

    @Override
    protected int select() throws Exception {
        return selector.select();
    }

    protected void init() throws IOException {
        selector = Selector.open();

    }


}
