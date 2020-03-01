package love.moon.load.client;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

/**
 * Author: lovemooner
 * Date: 2017/5/25 15:43
 */
public class IOClient {

    public static final int PORT = 8877;


    public void connect() throws IOException {
        Socket socket=null;
        PrintStream ps=null;
        try {
            socket = new Socket("127.0.0.1", PORT);
            OutputStream os = socket.getOutputStream();
            ps = new PrintStream(os);
            ps.println("Hi Service");//服务器端简单处理一下。
        } catch (Exception rr) {
            rr.printStackTrace();
        } finally {
            ps.close();
            socket.close();

        }
    }


}
