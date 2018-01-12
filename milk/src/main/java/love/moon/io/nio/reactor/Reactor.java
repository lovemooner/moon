package love.moon.io.nio.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 每个成功连接后的Channel的所有操作由同一个线程处理。
 * 这样保证了同一请求的所有状态和上下文在同一个线程中，避免了不必要的上下文切换，同时也方便了监控请求响应状态
 * User: lovemooner
 * Date: 17-4-11
 * Time: 下午3:54
 */
public class Reactor {
    private static Logger LOGGER = LoggerFactory.getLogger(Reactor.class);

    public static int PORT = 8888;
    private Executor executor = Executors.newCachedThreadPool();
    private final Processor[] pool;
    private static final int DEFAULT_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    private ServerSocketChannel serverSocketChannel;

    private boolean selectable = false;

    Reactor() throws IOException, InterruptedException {
        this.pool = new Processor[DEFAULT_SIZE];
        for (int i = 0; i < pool.length; i++) {
//            pool[i] = new Processor();
        }
        this.executor = Executors.newCachedThreadPool();
    }

    public void start(int port) throws IOException {
        selectable = true;
        executor.execute(new Acceptor(new InetSocketAddress(port)));
        LOGGER.info("Server:Server startup");
    }


    class Acceptor implements Runnable {
        private Selector selector;

        private boolean flag = true;
        private InetSocketAddress socketAddress;


        public Acceptor(InetSocketAddress socketAddress) throws IOException {
            this.socketAddress = socketAddress;
            selector = Selector.open();
        }

        public void run() {
            try {
                serverSocketChannel = ServerSocketChannel.open();
//                serverSocketChannel.bind(socketAddress);
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                while (selectable) {
                    int selected = selector.select();
                    if (selected > 0) {
                        Set<SelectionKey> keys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = keys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            iterator.remove();
                            if (selectionKey.isValid() && selectionKey.isAcceptable()) {
                                ServerSocketChannel sc = (ServerSocketChannel) selectionKey.channel();
                                SocketChannel socketChannel = sc.accept();
                                if (socketChannel == null) {
                                    continue;
                                }
//                                LOGGER.info("Server->establish connect,Client IP:{}", socketChannel.getRemoteAddress());
                                synchronized (this) {
                                    executor.execute(new Processor(socketChannel));
                                }
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);

            }
        }
    }

    class Processor implements Runnable {
        private Selector selector;

        public Processor(SocketChannel socketChannel) throws IOException {
            selector = Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }

        @Override
        public void run() {
            try {

                for (; ; ) {
                    int selected = selector.select(1000);
//                    LOGGER.info("Server-> Processor select:{},Thread Name:{}", selected,Thread.currentThread().getName());
                    if (selected > 0) {
                        Set<SelectionKey> keys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = keys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            iterator.remove();
                            handle(selectionKey);
                        }
                        Thread.sleep(1000L);
                    }
                }

            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }



    }

    private void handle(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer);
            LOGGER.info("Server-> Received message {}", new String(buffer.array()));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Reactor reactor = new Reactor();
        reactor.start(PORT);
    }

}
