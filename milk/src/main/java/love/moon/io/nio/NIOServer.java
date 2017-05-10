package love.moon.io.nio;

import org.apache.log4j.Logger;

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
    private static Logger LOGGER = Logger.getLogger(NIOServer.class);
    /*标识数字*/
    private static int flag = 0;
    private static int PORT = 8888;
    /*缓冲区大小*/
    private static int BLOCK = 4;
    /*接受数据缓冲区*/
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);

    private Selector selector;

    public NIOServer(int port) throws IOException {
        selector = Selector.open();
        System.out.println(selector.select());
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        LOGGER.info("Server: Start at port 8888:");
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
            LOGGER.info("Accept request from {}" + socketChannel.getRemoteAddress());
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);
            if (count <= 0) {
                socketChannel.close();
                selectionKey.cancel();
                LOGGER.info("Received invalide data, close the connection");
            }
            LOGGER.info("Received message {}" + new String(buffer.array()));
        } else if (selectionKey.isWritable()) {
            int capacity = sendBuffer.capacity();
            sendBuffer.clear();
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            String sendText = "message from server" + flag++;
            if (sendText.length() > capacity) {
                sendText = sendText.substring(0, capacity);
            }
            sendBuffer.put(sendText.getBytes());
            sendBuffer.flip();
            socketChannel.write(sendBuffer);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) throws IOException {
        NIOServer selector = new NIOServer(PORT);
        selector.listen();

    }
}
