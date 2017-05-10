package love.moon.io.nio.reactor.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
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
public class Reactor implements Runnable {

    private Executor executor = Executors.newCachedThreadPool();

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    Reactor(int port) throws IOException {
        // 初始化ServerSocketChannel，以非阻塞模式运行
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        // 初始化Selector
        selector = Selector.open();
        // 将ServerSocketChannel注册到Selector上
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 在SelectionKey上绑定一个附属对象Acceptor
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 阻塞直至事件就绪
                selector.select();
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
        public void run() {
            try {
                // 获取新连接的SocketChannel
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    // 具体的处理逻辑
                    executor.execute(new Handler(selector, socketChannel));
                }
            } catch (IOException ex) {
            }
        }
    }
}
