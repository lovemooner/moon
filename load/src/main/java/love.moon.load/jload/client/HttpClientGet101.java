package love.moon.load.jload.client;

import love.moon.load.jload.bean.Config;
import love.moon.load.jload.bean.Result;
import love.moon.load.jload.monitor.IResponseLogger;
import love.moon.load.jload.monitor.ResponseLogger;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2018/8/23 13:23
 */
public  class HttpClientGet101 implements IClient {

    private IResponseLogger responseLogger = new ResponseLogger();

    public IResponseLogger getResponseLogger() {
        return responseLogger;
    }

    public void setResponseLogger(IResponseLogger responseLogger) {
        this.responseLogger = responseLogger;
    }

    public void request() {
        CloseableHttpClient httpclient = createHttpClient(Config.isProxy);
//        HttpGet httpGet = mockBrowserGet(Config.url+"/"+Config.count.incrementAndGet());
        HttpGet httpGet = mockBrowserGet(Config.URL_SLC11FSP_l);
        while (true) {
            final Result result = doRequest(httpclient, httpGet);
            responseLogger.log(result);
            if (!Config.keepAlive) {
                close(httpclient);
                break;
            }

        }

    }


    private Result doRequest(CloseableHttpClient httpclient, HttpGet httpGet) {
        Result result = new Result();
        try {
            result.setRequestTime(System.currentTimeMillis());
//            System.out.println("send Get,Url "+httpGet.getURI());
//            System.out.println("send Get");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                result.setSuccess(true);
            } else {
                System.out.println("ERROR,Status Code :" + statusLine.getStatusCode());
                result.setSuccess(false);
            }
            if (entity != null) {
//                    System.out.println("Response content length: " + entity.getContentLength());
                String responseStr = "Response content: " + EntityUtils.toString(entity);
//                    System.out.println(responseStr); // 打印响应内容
            }
            result.setRespondTime(System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    public void close(CloseableHttpClient httpclient) {
        // 关闭连接,释放资源
        try {
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected HttpGet mockBrowserGet(String url) {
        HttpGet httpGet = new HttpGet(url);
//        httpGet.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
//        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
//        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
//        httpGet.setHeader("Connection", "keep-alive");
//        httpGet.setHeader("Cookie", "");
//        httpGet.setHeader("Host", "");
//        httpGet.setHeader("refer", "");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        //            getMethod.getParams()
//                    .setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        return httpGet;
    }

    public CloseableHttpClient createHttpClient(boolean isProxy) {
        CloseableHttpClient httpclient;
        if (isProxy) {
            HttpHost proxy = new HttpHost("cn-proxy.jp.oracle.com", 80, "http");
            RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
            //实例化CloseableHttpClient对象
            httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        } else {
            httpclient = HttpClients.createDefault();
        }
        return httpclient;
    }


}
