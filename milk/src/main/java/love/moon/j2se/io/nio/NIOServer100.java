package love.moon.j2se.io.nio;

import love.moon.j2se.io.IOConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * single selector
 * User: lovemooner
 * Date: 17-4-10
 * Time: 下午5:25
 */
public class NIOServer100 {
    /*缓冲区大小*/
    private static int BLOCK = 100;
    /*接受数据缓冲区*/
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);

    private Selector selector;

    public NIOServer100(int port) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server: Start at port:" + port);
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

    Random r = new Random();

    private String getHttpResponse() {
        String content = "hello " + r.nextInt(1000);
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type:text/html" + "\r\n");
        sb.append("Content-Length:" + content.length() + "\r\n");
        sb.append("\r\n");
        sb.append(content);
        return sb.toString();
    }
    private void dispatch(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isConnectable()) {
            if (selectionKey.attachment() == null) {
                selectionKey.attach(r.nextInt(1000));
            }
            System.out.println("a connection was established with a remote server.");
        } else if (selectionKey.isAcceptable()) {
            // a connection was accepted by a ServerSocketChannel.
            ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = acceptServerSocketChannel.accept();
            socketChannel.configureBlocking(false);
            System.out.println("Accept request from "+ socketChannel.getRemoteAddress());
            socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(500);
            int count = socketChannel.read(buffer);
            if (count <= 0) {
                socketChannel.close();
                selectionKey.cancel();
                System.out.println("Received invalid data, close the connection");
            }
            System.out.println("Print Request ");
            System.out.println(new String(buffer.array()));
//            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } else if (selectionKey.isWritable()) {
            int capacity = sendBuffer.capacity();
            sendBuffer.clear();
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            String sendText = getHttpResponse();
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
        NIOServer100 selector = new NIOServer100(IOConfig.PORT);
        selector.listen();

    }
}
