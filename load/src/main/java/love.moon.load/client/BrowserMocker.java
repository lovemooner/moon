package love.moon.load.client;

import love.moon.load.jload.bean.Config;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2018/8/20 17:41
 */
public class BrowserMocker {

    public void testSend() {
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new HttpClientGet());
            t.start();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        BrowserMocker mocker = new BrowserMocker();
        mocker.testSend();
        System.out.println("end");
//        while (true){
//            Thread.sleep(100);
//        }
    }


    class HttpClientGet implements Runnable {

        public void getResult(CloseableHttpResponse response) throws IOException {
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------start------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    System.out.println("Response content: " + EntityUtils.toString(entity)); // 打印响应内容
                }
                System.out.println("-----------------end-------------------");
            } finally {
                response.close();
            }
        }

        public void run() {

            try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                // 创建httpget.
                HttpGet httpget = new HttpGet(Config.URL_SLC11FSP);
                System.out.println("executing request " + httpget.getURI());
                // 执行get请求.
                while (true) {
                    getResult(httpclient.execute(httpget));
                    Thread.sleep(100l);
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
