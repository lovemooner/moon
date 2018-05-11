package love.moon.io;

import org.junit.Test;

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
        for(int i=0;i<1;i++){
            new IOClient().start();
        }


        Vector v=new Vector(10);
        for (int i=1;i<100; i++){
            Object o=new Object();
            v.add(o);
            o=null;
        }

    }

}
