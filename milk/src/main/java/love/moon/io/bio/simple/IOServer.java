package love.moon.io.bio.simple;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

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
public class IOServer {

    private static Logger LOG = Logger.getLogger(IOServer.class);

    ServerSocket serverSocket = null;

    public IOServer() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(2345));
        LOG.info("Server  start");
    }

    public void listening() throws IOException {
        while (true) {
            Socket socket = null;  //block at here
            socket = serverSocket.accept();
            LOG.info("server:accept" + socket.getRemoteSocketAddress());
            try {
                int readSize;
                byte[] readBuf = new byte[1024];
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                while ((readSize = in.read(readBuf)) != -1) {   //block at here
                    LOG.info("server:read");
                    System.out.println(new String(readBuf));

                }
            } catch (IOException e) {
                LOG.error("Client is Exception");
            }
            LOG.info("finish=============");
        }
    }


    public static void main(String[] args) {
        IOServer server = null;
        try {
            server = new IOServer();
            server.listening();
        } catch (IOException e) {
            LOG.error(e);
        }

    }

}