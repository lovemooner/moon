package bio;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private static Logger LOGGER = Logger.getLogger(IOServer.class);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(2345));
            LOGGER.info("Server  start");
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                int readSize;
                byte[] readBuf = new byte[1024];
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                while ((readSize = in.read(readBuf)) != -1) {
                   System.out.println(new String(readBuf));
                    out.write(readBuf, 0, readSize);

                }
            }
        } catch (IOException ex) {
            LOGGER.error(ex);

        }
    }

}