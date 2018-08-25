package love.moon.load.jload.client;

import love.moon.load.jload.bean.Config;
import love.moon.load.jload.bean.Result;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2018/8/20 16:05
 */
public class HttpClientGet100 extends AbsClient {


//    public Result doRequest() {
//
//
//        Result result = new Result();
//        try {
//            result.setStartTime(System.currentTimeMillis());
//            CloseableHttpResponse response = httpclient.execute(requestBase);
//            try {
//                HttpEntity entity = response.getEntity();
//                StatusLine statusLine = response.getStatusLine();
//                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
//                    result.setSuccess(true);
//                } else {
//                    System.out.println("ERROR,Status Code :" + statusLine.getStatusCode());
//                }
//                if (entity != null) {
////                    System.out.println("Response content length: " + entity.getContentLength());
////                    System.out.println("Response content: " + EntityUtils.toString(entity)); // 打印响应内容
//                }
//            } finally {
//                response.close();
//            }
//            result.setEndTime(System.currentTimeMillis());
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

}
