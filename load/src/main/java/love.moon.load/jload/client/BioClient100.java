package love.moon.load.jload.client;

import love.moon.load.jload.bean.Config;
import love.moon.load.jload.bean.Result;
import love.moon.load.jload.monitor.IResponseLogger;
import love.moon.load.jload.monitor.ResponseLogger;

import java.io.*;
import java.net.Socket;

/**
 * Author: lovemooner
 * Date: 2018/8/26 1:05
 */
public class BioClient100 implements IClient{

    public static String host = Config.URL_SLC11FSP_HOST;
    public static int port = 7878;
    private IResponseLogger responseLogger = new ResponseLogger();


    public void request() {
        Result result = new Result();
        try {
            Socket socket = new Socket(host, port);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            PrintStream ps = new PrintStream(os);
            int count = 0;
            while (true) {
                result.setRequestTime(System.currentTimeMillis());
//                ps.println("Hi Service" + count+"\r\n");//服务器端简单处理一下。
                ps.println("Hi Service" + count);//服务器端简单处理一下。
                count++;
                String temp = br.readLine();
//                System.out.println("recv:"+temp);
                result.setRespondTime(System.currentTimeMillis());
                result.setSuccess(true);
                responseLogger.log(result);
            }
//            ps.close();
//            br.close();
//            socket.close();
        } catch (Exception rr) {
            rr.printStackTrace();
        }

    }

}
