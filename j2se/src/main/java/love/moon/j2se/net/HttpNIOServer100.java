package love.moon.j2se.net;

import lombok.extern.slf4j.Slf4j;
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
import java.util.UUID;

/**
 * single selector
 * User: lovemooner
 * Date: 17-4-10
 * Time: 下午5:25
 */
@Slf4j
public class HttpNIOServer100 {

    /*缓冲区大小*/
    private static int BLOCK = 100;
    /*接受数据缓冲区*/

    private Selector selector;

    public HttpNIOServer100(int port) throws IOException {
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

    private String getResponseStr(String sessionId) {
        String content = "hello " + sessionId;
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type:text/html" + "\r\n");
        sb.append("Content-Length:" + content.length() + "\r\n");
        sb.append("\r\n");
        sb.append(content);
        return sb.toString();
    }

    private void dispatch(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = acceptServerSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.finishConnect();

            log.info("Accept request from {}", socketChannel.getRemoteAddress());
            Request request = new Request();
            request.setSocketChannel(socketChannel);
            request.setRequestId(UUID.randomUUID().toString());
            SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            key.attach(request);
        } else if (selectionKey.isReadable()) {
            Request request = (Request) selectionKey.attachment();
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(500);
            int count = socketChannel.read(buffer);
            if (count <= 0) {
                socketChannel.close();
                selectionKey.cancel();
                System.out.println("Received invalid data, close the connection");
                return;
            }
            log.info("read,requestId:{},content:\r\n{}", request.getRequestId(), new String(buffer.array()));
            request.setReaded(true);
            selectionKey.attach(request);
        }else if(selectionKey.isWritable()){
            //response
            Request request = (Request) selectionKey.attachment();
            ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
            int capacity = sendBuffer.capacity();
            sendBuffer.clear();
            String sendText = getResponseStr(request.getRequestId());
            if (sendText.length() > capacity) {
                sendText = sendText.substring(0, capacity);
            }
            sendBuffer.put(sendText.getBytes());
            sendBuffer.flip();
            SocketChannel socketChannel= request.getSocketChannel();
            socketChannel.write(sendBuffer);

        }
    }

    public static void main(String[] args) throws IOException {
        HttpNIOServer100 selector = new HttpNIOServer100(IOConfig.PORT);
        selector.listen();

    }
}
