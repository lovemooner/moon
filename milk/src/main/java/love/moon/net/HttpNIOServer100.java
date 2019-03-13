package love.moon.net;

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
import java.util.Random;
import java.util.Set;

/**
 * single selector
 * User: lovemooner
 * Date: 17-4-10
 * Time: 下午5:25
 */
public class HttpNIOServer100 {


    private Selector selector;

    public HttpNIOServer100(int port) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server: Start at port: "+port);
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

    Random random = new Random();

    private void dispatch(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = acceptServerSocketChannel.accept();
            socketChannel.configureBlocking(false);
            System.out.println("Accept request from " +socketChannel.getRemoteAddress());
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(500);
            int count = socketChannel.read(buffer);
            if (count <= 0) {
                socketChannel.close();
                selectionKey.cancel();
                System.out.println("Received invalid data, close the connection");
            }
            System.out.println("Print request-----------------------------------" );
            System.out.println(new String(buffer.array()));
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } else if (selectionKey.isWritable()) {
            String content="hello "+ random.nextInt(1000);
            StringBuilder sb=new StringBuilder();
            sb.append("HTTP/1.1 200 OK\r\n");
            sb.append("Content-Type:text/html"  + "\r\n");
            sb.append("Content-Length:" + content.length() + "\r\n");
            sb.append("\r\n");
            sb.append(content);
           ByteBuffer sendBuffer = ByteBuffer.allocate(sb.length());
            sendBuffer.put(sb.toString().getBytes());
            sendBuffer.flip();

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            socketChannel.write(sendBuffer);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) throws IOException {
        HttpNIOServer100 selector = new HttpNIOServer100(IOConfig.PORT);
        selector.listen();

    }
}
