package love.moon.load.client;


import love.moon.load.jload.bean.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2017/6/28 18:27
 */
public class HttpReqMocker {



    public static class Request implements Runnable{

        public void run() {
            while (true) {
                String responseStr=sendGet(Config.URL_SLC11FSP, null);
                System.out.println(responseStr);
//                try {
//                    Thread.sleep(6000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public void test(HttpURLConnection connection){
        StringBuilder sb=new StringBuilder();

    }

    public static String sendGet(String url, String param) {
        BufferedReader in = null;
//        HttpURLConnection  connection=null;
        HttpURLConnection connection=null;
        StringBuilder result = new StringBuilder();
        try {
            if (param == null) {
                param = "";
            }
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            connection = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            StringBuilder responseStr=new StringBuilder();
            for (String key : map.keySet()) {
                responseStr.append (key + "--->" + map.get(key));
            }
//            System.out.println(responseStr.toString());
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
//                    in.close();
                }
                if(connection!=null){
//                    connection.disconnect();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws InterruptedException {

        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(10L);
            cachedThreadPool.execute(new Thread(new Request()));
        }
    }
}
