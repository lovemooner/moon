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
public class IOServerSingleThread {

    private static Logger LOG = Logger.getLogger(IOServerSingleThread.class);


    public void openServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(2345));
            LOG.info("Server  start");
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
                LOG.info("finish=============");
            }
        } catch (IOException ex) {
            LOG.error(ex);

        }
    }


    public static void main(String[] args) {
        IOServerSingleThread server=new IOServerSingleThread();
        server.openServer();
        try {
            Socket  socket = new Socket("localhost", 2345);
            OutputStream out = socket.getOutputStream();
            int count=0;
            while (true) {
                out.write(("Hello"+count++).getBytes());
                Thread.sleep(1000L);

            }
        } catch (IOException e) {
            LOG.error(e);
        } catch (InterruptedException e) {
            LOG.error(e);
        }
    }

}