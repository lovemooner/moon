package love.moon.load.jload.client.nio;

import love.moon.load.jload.bean.Config;
import love.moon.load.jload.bean.Result;
import love.moon.load.jload.client.IClient;
import love.moon.load.jload.monitor.IResponseLogger;
import love.moon.load.jload.monitor.ResponseLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lovemooner
 * Date: 2018/8/28 15:50
 */
public class JNioClient100 implements IClient {


    private IResponseLogger responseLogger = new ResponseLogger();
    private Map<String, Result> resultMap = new HashMap<String, Result>();

    public void request() {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            // 注册连接服务端socket动作
            socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            InetSocketAddress SERVER_ADDRESS = new InetSocketAddress(Config.URL_SLC11FSP_HOST, Config.SOCKET_PORT);
            socketChannel.connect(SERVER_ADDRESS);

            while (true) {
                if (selector.select() > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isConnectable()) {
                            accept(selector, selectionKey);
                        } else if (selectionKey.isReadable()) {
                            read(selectionKey);
                        }
                        iterator.remove();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void accept(Selector selector, SelectionKey selectionKey) throws IOException, InterruptedException {
        System.out.println("client connect...");
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        if (socketChannel.isConnectionPending()) {
            socketChannel.finishConnect();
            System.out.println("client connected");
        }
        socketChannel.register(selector, SelectionKey.OP_READ); //| SelectionKey.OP_WRITE
        doWrite(socketChannel);
    }

    public void doWrite(final SocketChannel socketChannel){
//          ExecutorService executor= Executors.newCachedThreadPool();
//        executor.execute(new Runnable() {
//                public void run() {
//                }
//            });
        for(int i=0;i<40;i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            if(resultMap.size()>200){
                                Thread.sleep(100l);
//                                System.out.println("Thead sleep");
                            }
                            write(socketChannel);
                            Thread.sleep(1l);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static final AtomicInteger count = new AtomicInteger();

    protected void write(SocketChannel sc) throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(512);
        String result_id = String.valueOf(count.incrementAndGet());
        String sendText = result_id + "\n";
        buffer.put(sendText.getBytes());
        //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
        buffer.flip();
        Result result = new Result();
        result.setRequestTime(System.currentTimeMillis());
        resultMap.put(result_id, result);
        sc.write(buffer);
    }


    public void read(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //将缓冲区清空以备下次读取
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //读取服务器发送来的数据到缓冲区中
        int count = socketChannel.read(buffer);
        if (count > 0) {
            String receiveText = new String(buffer.array(), 0, count);
//            System.out.println("Client->Receive:" + receiveText);
           String[] arr=  receiveText.split("\r\n");
           for(int i=0;i<arr.length;i++){
               Result result = resultMap.get(arr[i]);
               if (result != null) {
                   result.setRespondTime(System.currentTimeMillis());
                   result.setSuccess(true);
                   responseLogger.log(result);
                   resultMap.remove(receiveText);
               }
           }
        }

    }

    public static void main(String[] args) {
        JNioClient100 client100 = new JNioClient100();
        client100.request();
    }

}
