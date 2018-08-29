package love.moon.load.jload.bean;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lovemooner
 * Date: 2018/8/24 15:00
 */
public class Config {

//    public static final String url = LoadConfig.URL_SLC11FSP_l;
    public final static String URL_SLC11FSP_HOST="slc11fsp.us.oracle.com";
    public final static String URL_LOCAL_HOST="127.0.0.1";

    public final static String URL_SLC11FSP = "http://slc11fsp.us.oracle.com:8080/papa/cart/list";
    public final static String URL_SLC11FSP_l = "http://slc11fsp.us.oracle.com:8080/papa/cart/list2";
    public final static String URL_LOCALHOST = "http://127.0.0.1:8080/papa/cart/list";

    //        url="http://www.google.com/";
    public static final boolean isProxy = false;
    public static final boolean keepAlive = true;
    public static final int threadNum = 200;

    public static long MONITOR_INTERVAL = 1000l;

    public static final AtomicInteger count = new AtomicInteger();

    public final static int NIO_HTTP_PORT=7878;
    public final static int SOCKET_PORT=7878;



}
