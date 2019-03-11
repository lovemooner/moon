package love.moon.net;

/**
 * Author: lovemooner
 * Date: 2018/8/29 17:34
 */

import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;

import java.io.IOException;

public class HttpClient100 {

    private final String USER_AGENT = "Mozilla/5.0";
    private static String url = "http://192.168.1.111:8080/papa/test/mockBlockRequest/1";
    private static String url2 = "http://localhost:8001/test";
    private static String url3 = "http://slc11fsp.us.oracle.com:8080/papa";


    public static void main(String[] args)  {
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" send...");
                HttpResponse response = null;
                response = HttpUtil.sendGet(url);
                System.out.println("result:"+response.getContent());
            }).start();
        }
    }

}

