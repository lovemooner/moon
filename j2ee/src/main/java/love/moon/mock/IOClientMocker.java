package love.moon.mock;

import love.moon.util.RandomUtil;

import java.io.*;
import java.net.Socket;

/**
 * Author: lovemooner
 * Date: 2017/5/25 15:43
 */
public class IOClientMocker extends Thread {


    public void run() {
        Socket socket = null;
        InputStream is;
        BufferedReader br = null;
        OutputStream os;
        PrintStream ps = null;
        try {
            socket = new Socket(MockConfig.HOST, MockConfig.PORT);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(is));
            ps = new PrintStream(os);
            while (true) {
                String str=RandomUtil.random();
                System.out.println("client->send msg:"+str);
                ps.println("Hi Service;" +str );//服务器端简单处理一下。
//                Thread.sleep(1000L);
            }

        } catch (Exception rr) {
            rr.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            try {
                br.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new IOClientMocker().start();
        }
    }


}
