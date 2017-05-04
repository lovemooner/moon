package love.moon.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Thread Per Request
 * 一个请求创建2个线程
 * 1.request
 * 2.handle IO
 * User: lovemooner
 * Date: 17-4-7
 * Time: 下午4:52
 */
public class IOServerMultiThread implements Runnable {
    private final int port;
    private ServerSocket serverSocket;

    public IOServerMultiThread(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //创建一个单独的线程处理其I/O操作
                new Thread(new Handler(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public static void main(String[] args) {
        new Thread(new IOServerMultiThread(9001)).start();
    }




    class Handler implements Runnable {
        private final Socket clientSocket;

        public Handler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            int readSize;
            byte[] readBuf = new byte[1024];
            try {
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                while ((readSize = in.read(readBuf)) != -1) {
                    out.write(readBuf, 0, readSize);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
