package love.moon.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Single Thread
 * 1.accept
 * 2.handle IO
 * User: lovemooner
 * Date: 17-3-28
 * Time: 上午11:02
 */
public class BioServer {

    private static Logger LOG = LoggerFactory.getLogger(BioServer.class);

    ServerSocket serverSocket = null;

    public BioServer() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(MockConfig.PORT));
        LOG.info("Server is  started at PORT:{}",MockConfig.PORT);
    }

    public void listening() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            LOG.info("server:accept" + socket.getRemoteSocketAddress());
            final Socket finalSocket = socket;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] readBuf = new byte[1024];
                        InputStream in = finalSocket.getInputStream();
                        OutputStream out = finalSocket.getOutputStream();
                        int readSize=0;
                        while ((readSize = in.read(readBuf)) != -1) {   //block at here
                            LOG.info("Server-> Receiver Msg:{}",new String(readBuf));
                        }
                        Thread.sleep(1000L);
                    } catch (IOException e) {
                        LOG.error("Client is Exception");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            LOG.info("finish=============");
        }
    }


    public static void main(String[] args) {
        BioServer server = null;
        try {
            server = new BioServer();
            server.listening();
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
        }

    }

}