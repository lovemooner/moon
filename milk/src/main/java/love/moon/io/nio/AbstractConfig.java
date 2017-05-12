package love.moon.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Author: lovemooner
 * Date: 2017/5/12 14:58
 */
public abstract class AbstractConfig {
    protected int flag = 0;
    public static int PORT = 8888;


    protected void sendMsg(SocketChannel sc) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String sendText = Thread.currentThread().getName() + "-" + (flag++) + " ";
        buffer.put(sendText.getBytes());
        //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
        buffer.flip();
        sc.write(buffer);
    }
}
