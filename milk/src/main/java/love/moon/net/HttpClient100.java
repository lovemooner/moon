package love.moon.net;

/**
 * Author: lovemooner
 * Date: 2018/8/29 17:34
 */

import love.moon.util.HttpUtil;

public class HttpClient100 {

    private final String USER_AGENT = "Mozilla/5.0";
    private static String url = "http://localhost:8080/papa/test/mockBlockRequest";
    private static String url2 = "http://localhost:8001/test";
    private static String url3 = "http://slc11fsp.us.oracle.com:8080/papa";


    public static void main(String[] args)  {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" send...");
                    String result = HttpUtil.sendGet(url, null);
                    System.out.println(result);
                }
            }).start();
        }
    }

}

