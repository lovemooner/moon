package love.celery.mina;

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
public class MinaReactor implements Runnable {

    private Executor executor = Executors.newCachedThreadPool();
    private static final long SELECT_TIMEOUT = 1000L;

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    MinaReactor(int port) throws IOException {
        // 初始化ServerSocketChannel，以非阻塞模式运行
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        // 初始化Selector
        selector = Selector.open();
        // 将ServerSocketChannel注册到Selector上
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 在SelectionKey上绑定一个附属对象Acceptor
//        selectionKey.attach(new Acceptor());
    }

    protected int select(long timeout) throws Exception {
        return selector.select(timeout);
    }

    @Override
    public void run() {
        try {
            while (selector.select()>0) {
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



    public class Processor implements Runnable {
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
                for (; ; ) {
                    int selected = select(SELECT_TIMEOUT);
                    if (selected < 0) {
                        continue;
                    }

                    if (state == READING) {
                        read();
                    } else if (state == SENDING) {
                        send();
                    }
                }
            } catch (IOException ex) { /* ... */ } catch (Exception e) {
                e.printStackTrace();
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
