package love.moon.load.jmeter.java;

import love.moon.load.jload.bean.Config;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/26 14:16
 */
public class JHttpRequest101 extends AbstractJavaSamplerClient {

    private HttpClientGet clientGet;

    public Arguments getDefaultParameters() {
        Arguments args = new Arguments();
        args.addArgument("req_url", Config.URL_SLC11FSP);
        return args;
    }


    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        clientGet = new HttpClientGet();
        ;
    }

    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult sr = new SampleResult();
        String url = context.getParameter("req_url");
        HttpClientGet clientGet = new HttpClientGet();
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
//            while (true) {
            sr.sampleStart();
            clientGet.getResult(httpclient.execute(httpget));
            Thread.sleep(100l);
            sr.sampleEnd();
//            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sr.setSuccessful(true);
        return sr;
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        this.clientGet.close();  // 关闭连接
    }

    class HttpClientGet {


        public void doSend(String url) {

            try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                // 创建httpget.
                HttpGet httpget = new HttpGet(url);
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

        public void close() {

        }

        public void getResult(CloseableHttpResponse response) throws IOException {
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------start------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
//                    System.out.println("Response content length: " + entity.getContentLength());
//                    System.out.println("Response content: " + EntityUtils.toString(entity)); // 打印响应内容
                    System.out.println("receive response,time:" + System.currentTimeMillis());
                }
                System.out.println("-----------------end-------------------");
            } finally {
                response.close();
            }
        }


    }
}
