package love.moon.io.nio.reactor.simple;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * User: lovemooner
 * Date: 17-4-11
 * Time: 下午3:56
 */
public class Handler implements Runnable {
    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel c) throws IOException {
        socket = c;
        socket.configureBlocking(false);
        // 继续在Selector上注册读事件，此时attachment为当前Handler实例
        sk = socket.register(sel, SelectionKey.OP_READ, this);
        // 使选择器上的第一个还没有返回的选择操作立即返回
        sel.wakeup();
    }

    boolean inputIsComplete() {
        return true;
    }

    boolean outputIsComplete() {
        return true;
    }

    void process() {
        System.out.println("Handle processing...");
    }

    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) { /* ... */ }
    }

    void read() throws IOException {
        System.out.println("Handle reading...");
        socket.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            // 在SelectionKey上注册写事件
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        System.out.println("Handle writing...");
        socket.write(output);
        if (outputIsComplete()) {
            //write完就结束了, 关闭select key
            sk.cancel();
        }
    }
}
