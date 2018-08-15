package love.moon.io.bio.simple;

import love.moon.io.IOConfig;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

/**
 * Author: lovemooner
 * Date: 2017/5/25 15:43
 */
public class IOClient extends Thread{


    public void run() {
        try {
            Socket socket = new Socket(IOConfig.HOST, IOConfig.PORT);
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

    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            new IOClient().start();
        }
    }

}
