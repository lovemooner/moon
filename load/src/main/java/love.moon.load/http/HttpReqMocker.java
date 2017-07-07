package love.moon.load.http;

import love.moon.util.HttpUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: lovemooner
 * Date: 2017/6/28 18:27
 */
public class HttpReqMocker {



    public static void main(String[] args) throws InterruptedException {
//        final String url = "http://slc11fsp.us.oracle.com:8080/papa/cart/list2";
        final String url = "http://localhost:8080/papa/cart/list";
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 2000; i++) {
            Thread.sleep(100L);
            cachedThreadPool.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(HttpUtil.sendGet(url, null));
//                        try {
//                            Thread.sleep(1l);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }));
        }
    }
}
