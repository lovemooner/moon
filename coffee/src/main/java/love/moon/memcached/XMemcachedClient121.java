package love.moon.memcached;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Author: lovemooner
 * Date: 2017/10/17 17:13
 */
public class XMemcachedClient121 {

    private MemcachedClient client;

    public XMemcachedClient121() throws IOException {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("slc11fsp.us.oracle.com:11211"));
        client = builder.build();
    }

    public void test() throws InterruptedException, MemcachedException, TimeoutException {
        if (!client.set("hello", 0, "world")) {
            System.err.println("set error");
        }
//        System.out.println(client.get("hello"));
        if (client.add("hello", 0, "dennis")) {
            System.err.println("Add error,key is existed");
        }
        if (!client.replace("hello", 0, "dennis")) {
            System.err.println("replace error");
        }
        client.append("hello", " good");
        client.prepend("hello", "hello ");
//        System.out.println(client.get("hello"));
        client.deleteWithNoReply("hello");
//        System.out.println(client.get("hello"));
    }

    public void testCAS() throws InterruptedException, MemcachedException, TimeoutException {
        client.set("key1", 0, "world");
        GetsResponse<Integer> result = client.gets("key1");
        long cas = result.getCas();
        if (!client.cas("key1", 0, 2, cas)) {
            System.err.println("cas error");
        }
    }

    public void testOps() throws InterruptedException, MemcachedException, TimeoutException {
        client.set("hello", 0, "Hello,xmemcached");
        String value = client.get("hello");
        System.out.println("hello=" + value);

        client.delete("hello");
        value = client.get("hello");
        System.out.println("hello=" + value);
    }

    public void shutdown() throws IOException {
        client.shutdown();
    }

    public static void main(String[] args) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        XMemcachedClient121 demo = new XMemcachedClient121();
        demo.test();
        demo.shutdown();
    }
}
