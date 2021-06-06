package love.moon.j2se.net;

/**
 * Author: lovemooner
 * Date: 2018/8/29 17:34
 */

import com.google.common.base.Preconditions;
import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;

public class HttpClient100 {

    private final String USER_AGENT = "Mozilla/5.0";
    private static String url = "http://192.168.1.111:8080/papa/test/mockBlockRequest/1";
    private static String url2 = "http://localhost:8080/test";
    private static String url3 = "http://slc11fsp.us.oracle.com:8080/papa";


    public static void main(String[] args)  {
        Preconditions.checkNotNull("");
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" send...");
                HttpResponse response = HttpUtil.sendGet(url2);
                System.out.println("client receive response:"+response.getContent());
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}

