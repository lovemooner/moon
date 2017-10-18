package love.moon.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Author: lovemooner
 * Date: 2017/10/18 10:41
 */
public class MemCacheManager {

    private static MemcachedClient client;

    private  static void init() throws IOException {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("slc11fsp.us.oracle.com:11211"));
        client = builder.build();
    }

    public static boolean set(String key, Object val) {
        try {
            return client.set(key, 0, val);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
