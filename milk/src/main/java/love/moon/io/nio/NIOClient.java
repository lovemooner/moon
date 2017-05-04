package love.moon.io.nio;

/**
 * User: lovemooner
 * Date: 17-4-5
 * Time: 下午4:08
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {

    private static int PORT = 8888;
    /*标识数字*/
    private static int flag = 0;
    /*缓冲区大小*/
    private static int BLOCK = 4096;
    /*接受数据缓冲区*/
    private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);

    private SocketChannel client;

    private Selector selector;

    public NIOClient(int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        // 注册连接服务端socket动作
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        InetSocketAddress SERVER_ADDRESS = new InetSocketAddress(
                "localhost", port);
        socketChannel.connect(SERVER_ADDRESS);
    }


    /**
     *
     * @param selectionKey
     * @throws IOException
     */
    private void handleKey(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isConnectable()) {
            System.out.println("client connect");
            client = (SocketChannel) selectionKey.channel();
            if (client.isConnectionPending()) {
                client.finishConnect();
                System.out.println("完成连接!");
                sendbuffer.clear();
                sendbuffer.put("Hello,Server；".getBytes());
                sendbuffer.flip();
                client.write(sendbuffer);
            }
            client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            client = (SocketChannel) selectionKey.channel();
            //将缓冲区清空以备下次读取
            receivebuffer.clear();
            //读取服务器发送来的数据到缓冲区中
            int count = client.read(receivebuffer);
            if (count > 0) {
                String receiveText = new String(receivebuffer.array(), 0, count);
                System.out.println("client:" + receiveText);
                client.register(selector, SelectionKey.OP_WRITE);
            }

        } else if (selectionKey.isWritable()) {
            sendbuffer.clear();
            client = (SocketChannel) selectionKey.channel();
            String sendText = "message from client--" + (flag++);
            sendbuffer.put(sendText.getBytes());
            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
            sendbuffer.flip();
            client.write(sendbuffer);
            System.out.println("client：" + sendText);
            client.register(selector, SelectionKey.OP_READ);
        }
    }

    public void execute() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                handleKey(selectionKey);
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient(PORT);
        client.execute();
    }
}
