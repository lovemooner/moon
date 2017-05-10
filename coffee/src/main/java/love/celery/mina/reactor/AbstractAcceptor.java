package love.celery.mina.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2017/5/10 16:24
 */
public abstract class AbstractAcceptor {
    protected volatile Selector selector;
    private int port;

    private Executor executor;
    private final Processor[] pool;
    private static final int DEFAULT_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    private volatile boolean selectable;

    protected abstract void init() throws IOException;


    protected abstract int select() throws Exception;

    public AbstractAcceptor() throws IOException {
        this.pool =  new Processor[DEFAULT_SIZE];
        for (int i = 1; i < pool.length; i++) {
            pool[i]=new Processor();
        }
        this.executor = Executors.newCachedThreadPool();
        selectable = true;
        init();
    }

    private void startupAcceptor(){
        Acceptor acceptor = new Acceptor();
        executor.execute(acceptor);
    }

    protected void bind(){
        startupAcceptor();
    }


    class Acceptor implements Runnable {
        public void run() {
            try {
                while (selectable) {
                    int selected = select();
                    if (selected > 0) {

                    }

                }
                ServerSocketChannel channel =  ServerSocketChannel.open();
                channel.configureBlocking(false);
                channel.bind(new InetSocketAddress(port));
                SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);
                selectionKey.attach(new Acceptor());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class Processor implements Runnable {

        @Override
        public void run() {

        }
    }
}
