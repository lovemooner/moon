package love.moon.io.nio;

import love.moon.io.IOConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Author: lovemooner
 * Date: 2018/8/2 16:48
 */
public class NioBlockClient {



    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", IOConfig.PORT));
            socketChannel.configureBlocking(true);
            //write
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put("hello world11111111111111111!".getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);

            socketChannel.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}