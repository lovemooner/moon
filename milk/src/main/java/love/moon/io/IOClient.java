package love.moon.io;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

/**
 * Author: lovemooner
 * Date: 2017/5/25 15:43
 */
public class IOClient {


    @Test
    public void connect() {
        try {
            Socket socket = new Socket("127.0.0.1", IOConfig.PORT);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            PrintStream ps = new PrintStream(os);
            while (true) {
                ps.println("Hi Service");//服务器端简单处理一下。
                String temp = br.readLine();//读取客户端输入的消息
                if (temp.equals("bye")) {
                    //如果客户端输入bye,推出循环结束。
                    break;
                }
            }
            ps.close();
            br.close();
            socket.close();
        } catch (Exception rr) {
            rr.printStackTrace();
        }
    }


}