package love.moon.io.nio;

/**
 * User: lovemooner
 * Date: 17-4-5
 * Time: 下午4:08
 */


import love.moon.io.IOConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioNonBlockClient {


    private SocketChannel socketChannel;
    private static Selector selector;

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        // 注册连接服务端socket动作
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("localhost", IOConfig.PORT);
        socketChannel.connect(SERVER_ADDRESS);
        NioNonBlockClient client = new NioNonBlockClient();
        client.listen();

    }

    /**
     * @param selectionKey
     * @throws IOException
     */
    private void dispatch(SelectionKey selectionKey) throws IOException, InterruptedException {
        if (selectionKey.isConnectable()) {
            System.out.println("client connect");
            socketChannel = (SocketChannel) selectionKey.channel();
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put("Hello,Server；".getBytes());
                buffer.flip();
                socketChannel.write(buffer);
            }
            socketChannel.register(selector, SelectionKey.OP_READ);
//            new Thread(() -> {
//                while (true) {
//                    try {
//                        sendMsg(socketChannel);
//                        Thread.sleep(2000l);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
        } else if (selectionKey.isReadable()) {
            socketChannel = (SocketChannel) selectionKey.channel();
            //将缓冲区清空以备下次读取
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取服务器发送来的数据到缓冲区中
            int count = socketChannel.read(buffer);
            if (count > 0) {
                String receiveText = new String(buffer.array(), 0, count);
                System.out.println("Client->Receive:" + receiveText);
            }

        }
//        else if (selectionKey.isWritable()) {
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            client = (SocketChannel) selectionKey.channel();
//            String sendText = "message from client--" + (flag++);
//            buffer.put(sendText.getBytes());
//            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
//            buffer.flip();
//            client.write(buffer);
//            System.out.println("client：" + sendText);
//            client.register(selector, SelectionKey.OP_READ);
//        }
    }

    protected int flag = 0;

    protected void sendMsg(SocketChannel sc) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String sendText = Thread.currentThread().getName() + "-" + (flag++) + " \r\n";
        buffer.put(sendText.getBytes());
        buffer.flip();
        sc.write(buffer);
    }

    public void listen() throws IOException, InterruptedException {
        while (true) {
            int val = selector.select();
            if (val > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    dispatch(selectionKey);
                    iterator.remove();
                }
            }
        }
    }


}
