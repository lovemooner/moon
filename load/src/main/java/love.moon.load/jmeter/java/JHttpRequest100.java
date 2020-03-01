package love.moon.load.jmeter.java;

import love.moon.load.jload.bean.Config;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/26 14:16
 */
public class JHttpRequest100 extends AbstractJavaSamplerClient {

    private HttpRequester requester;
    public static ThreadLocal< CloseableHttpClient> httpClients = new ThreadLocal<CloseableHttpClient>();

    public Arguments getDefaultParameters() {
        Arguments args = new Arguments();
        args.addArgument("req_url", Config.URL_SLC11FSP);
        return args;
    }


    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        requester = new HttpRequester();
        ;
    }

    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult sr = new SampleResult();
        String url = context.getParameter("req_url");
        CloseableHttpClient httpClient= httpClients.get();
        if(httpClient==null){
            httpClient=HttpClients.createDefault();
            httpClients.set(httpClient);
        }
        try {
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            sr.sampleStart();
            requester.getResult(httpClient.execute(httpget));
            Thread.sleep(100l);
            sr.sampleEnd();
            sr.setSuccessful(true);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // sampleResult.samplePause();
        // sampleResult.sampleResume();
//            sr.setResponseData("from Response", null);
//            sr.setDataType(SampleResult.TEXT);

        return sr;
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        this.requester.close();  // 关闭连接
    }

    public class HttpRequester {

         CloseableHttpClient httpClient;


        public void close() {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void getResult(CloseableHttpResponse response) throws IOException {
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------start------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
//                    System.out.println("Response content length: " + entity.getContentLength());
                    System.out.println("Response content: " + EntityUtils.toString(entity)); // 打印响应内容
//                    System.out.println("receive response,time:" + System.currentTimeMillis());
                }
                System.out.println("-----------------end-------------------");
            } finally {
//                response.close();
            }
        }


    }
}
