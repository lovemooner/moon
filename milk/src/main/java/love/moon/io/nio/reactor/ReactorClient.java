package love.moon.io.nio.reactor;

import love.moon.io.nio.AbstractConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static love.moon.io.nio.reactor.Reactor.PORT;


/**
 * Author: lovemooner
 * Date: 2017/5/11 17:21
 */
public class ReactorClient extends AbstractConfig {

    private SocketChannel socketChannel;
    private Selector selector;

    public ReactorClient(int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        // 注册连接服务端socket动作
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("localhost", port);
        socketChannel.connect(SERVER_ADDRESS);
    }


    private void handleKey(SelectionKey selectionKey) throws IOException, InterruptedException {
        if (selectionKey.isConnectable()) {
            socketChannel = (SocketChannel) selectionKey.channel();
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
                socketChannel.register(selector, SelectionKey.OP_READ);
                while (true) {
                    sendMsg(socketChannel);
                    Thread.sleep(2000l);
                }
            }
        } else if (selectionKey.isReadable()) {
            socketChannel = (SocketChannel) selectionKey.channel();
            //将缓冲区清空以备下次读取
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取服务器发送来的数据到缓冲区中
            socketChannel.read(buffer);
            System.out.println("Client-> receive msg:" + new String(buffer.array()));
            while (true) {
                sendMsg(socketChannel);
                Thread.sleep(2000l);
            }
        }

    }


    public void execute() throws IOException, InterruptedException {
        while (true) {
            int selecteVal = selector.select();
            if (selecteVal > 0) {
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

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i <1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ReactorClient client = null;
                    try {
                        client = new ReactorClient(PORT);
                        client.execute();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}