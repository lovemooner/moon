package love.celery.mina.reactor;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/10 16:50
 */
public class App {

    public static void main(String[] args) throws IOException {
        SocketAcceptor acceptor=new SocketAcceptor();
        acceptor.bind();
    }
}
