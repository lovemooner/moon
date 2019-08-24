//package love.moon.memcached;
//
//
//import com.danga.MemCached.MemCachedClient;
//import com.danga.MemCached.SockIOPool;
//
//import java.net.InetSocketAddress;
//
///**
// * Author: lovemooner
// * Date: 2017/10/17 16:15
// */
//public class MemCache120 {
//
//    {
//        String[] servers = {"slc11fsp.us.oracle.com:11211"};
//        SockIOPool pool = SockIOPool.getInstance();
//        pool.setServers(servers);
//        pool.setFailover(true);
//        pool.setInitConn(10);
//        pool.setMinConn(5);
//        pool.setMaxConn(250);
//        pool.setMaintSleep(30);
//        pool.setNagle(false);
//        pool.setSocketTO(3000);
//        pool.setAliveCheck(true);
//        pool.initialize();
//    }
//
//    public static void main(String[] args) {
//        MemCachedClient memCachedClient = new MemCachedClient();
//        memCachedClient.set("key", "Hello!");
//        System.out.println(memCachedClient.get("key"));
//    }
//}
