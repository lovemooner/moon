package love.moon.load.nio.client;

/**
 * User: lovemooner
 * Date: 17-4-5
 * Time: 下午4:08
 */


import love.moon.load.jload.bean.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient100 implements INioClient {


    private SocketChannel socketChannel;
    private Selector selector;


    /**
     * @param selectionKey
     * @throws IOException
     */
    private void handleKey(SelectionKey selectionKey) throws IOException, InterruptedException {
        if (selectionKey.isConnectable()) {
            System.out.println("client connect...");
            socketChannel = (SocketChannel) selectionKey.channel();
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
                System.out.println("client connected");
            }
            socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE); //| SelectionKey.OP_WRITE
            new Thread(new Runnable() {
                public void run() {
                    try {
                        sendMsg(socketChannel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
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
    }

    public void execute() throws IOException, InterruptedException {
        while (true) {
            if (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    handleKey(selectionKey);
                    iterator.remove();
                }
            }
        }
    }


    protected int flag = 0;

    protected void sendMsg(SocketChannel sc) throws IOException, InterruptedException {
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String sendText = Thread.currentThread().getName() + "-" + (flag++) + "\n";
            buffer.put(sendText.getBytes());
            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
            buffer.flip();
            socketChannel.write(buffer);
            Thread.sleep(1000l);

        }
    }

    public void start(String host,int port) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            // 注册连接服务端socket动作
            socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            InetSocketAddress SERVER_ADDRESS = new InetSocketAddress(host,port);
            socketChannel.connect(SERVER_ADDRESS);
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
