package love.moon.io.nio.demo;

import love.moon.io.IOConfig;
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

/**
 * single selector
 * User: lovemooner
 * Date: 17-4-10
 * Time: 下午5:25
 */
public class NIOServer {
    private static Logger LOGGER = LoggerFactory.getLogger(NIOServer.class);
    /*标识数字*/
    private static int flag = 0;
    /*缓冲区大小*/
    private static int BLOCK = 4;
    /*接受数据缓冲区*/
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);

    private Selector selector;

    public NIOServer(int port) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        LOGGER.info("Server: Start at port: {}",port);
    }

    private void listen() throws IOException {
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                dispatch(selectionKey);
            }
        }
    }

    private void dispatch(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = acceptServerSocketChannel.accept();
            socketChannel.configureBlocking(false);
//            LOGGER.info("Accept request from {}" , socketChannel.getRemoteAddress());
            LOGGER.info("isAcceptable:"+ selectionKey.isAcceptable()+" isReadable:"+selectionKey.isReadable());
            socketChannel.register(selector, SelectionKey.OP_READ);
            LOGGER.info("isAcceptable:"+ selectionKey.isAcceptable()+" isReadable:"+selectionKey.isReadable());
        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(5);
            int count = socketChannel.read(buffer);
            if (count <= 0) {
                socketChannel.close();
                selectionKey.cancel();
                LOGGER.info("Received invalid data, close the connection");
            }
            LOGGER.info("Server-> Received message {}" , new String(buffer.array()));
        }
//        else if (selectionKey.isWritable()) {
//            int capacity = sendBuffer.capacity();
//            sendBuffer.clear();
//            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//            String sendText = "message from server" + flag++;
//            if (sendText.length() > capacity) {
//                sendText = sendText.substring(0, capacity);
//            }
//            sendBuffer.put(sendText.getBytes());
//            sendBuffer.flip();
//            socketChannel.write(sendBuffer);
//            socketChannel.register(selector, SelectionKey.OP_READ);
//        }
    }

    public static void main(String[] args) throws IOException {
        NIOServer selector = new NIOServer(IOConfig.PORT);
        selector.listen();

    }
}
