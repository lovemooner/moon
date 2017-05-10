package love.celery.mina.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
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

    private Executor executor = Executors.newCachedThreadPool();
    private final int port = 8020;
    private final Processor[] pool;
    private static final int DEFAULT_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    Reactor(int port) throws IOException {
        this.pool = new Processor[DEFAULT_SIZE];
//        for (int i = 1; i < pool.length; i++) {
//            pool[i] = new Processor(selector,serverSocketChannel);
//        }
        this.executor = Executors.newCachedThreadPool();
        executor.execute(new Acceptor(new InetSocketAddress(port)));
    }

    public void start() {
        try {
            while (selector.select() > 0) {
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    // 分发
                    dispatch((SelectionKey) (it.next()));
                }
                // 需要自己清除selectedKeys
                selected.clear();
            }
        } catch (IOException ex) {
        }
    }

    void dispatch(SelectionKey k) {
        /**
         * 获取SelectionKey中的attachment，并执行该attachment的run()方法
         * 拿第一个到达的socket连接来看，该attachment为一个Acceptor实例
         */
        Runnable r = (Runnable) (k.attachment());
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable {

        private InetSocketAddress socketAddress;

        public Acceptor(InetSocketAddress socketAddress) {
            this.socketAddress = socketAddress;
        }

        public void run() {
            try {
                // 初始化ServerSocketChannel，以非阻塞模式运行
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(socketAddress);
                serverSocketChannel.configureBlocking(false);
                selector = Selector.open();
                // 将ServerSocketChannel注册到Selector上
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                if (selector.select() > 0) {
                    Collection<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectedKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel sc = serverSocketChannel.accept();
                            executor.execute(new Processor(selector,sc));
                        }
                    }
                }
            } catch (IOException ex) {
            }
        }
    }

    class Processor implements Runnable {

        final SocketChannel socket;
        final SelectionKey sk;
        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer output = ByteBuffer.allocate(1024);
        static final int READING = 0, SENDING = 1;
        int state = READING;

        Processor(Selector sel, SocketChannel c) throws IOException {
            socket = c;
            socket.configureBlocking(false);
            // 继续在Selector上注册读事件，此时attachment为当前Handler实例
            sk = socket.register(sel, SelectionKey.OP_READ, this);
            // 使选择器上的第一个还没有返回的选择操作立即返回
            sel.wakeup();
        }

        boolean inputIsComplete() {
            return true;
        }

        boolean outputIsComplete() {
            return true;
        }

        void process() {
            System.out.println("Handle processing...");
        }

        @Override
        public void run() {
            try {
                if (selector.select() > 0) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (; ; ) {
                try {
                    if (state == READING) {
                        read();
                    } else if (state == SENDING) {
                        send();
                    }
                    Thread.sleep(5000L);
                } catch (IOException ex) { /* ... */ }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void read() throws IOException {
            System.out.println("Handle reading...");
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                // 在SelectionKey上注册写事件
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }

        void send() throws IOException {
            System.out.println("Handle writing...");
            socket.write(output);
            if (outputIsComplete()) {
                //write完就结束了, 关闭select key
                sk.cancel();
            }
        }
    }
}
