package love.moon.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

@SuppressWarnings("resource")
public class JedisTest {
    private static final String HASH_KEY = "love:moon";

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.6.100", 6379);
        //set key-value
        jedis.set(HASH_KEY, "value-nan");
        if (jedis.exists(HASH_KEY)) {
            System.out.println(jedis.get(HASH_KEY));
            jedis.del(HASH_KEY);
        }
      //hset
        jedis.hset(HASH_KEY, "username", "yourUsername");

        //存入多个key-value键值对
        Map<String, String> keyValueMap = new HashMap<String, String>();
        keyValueMap.put("password", "YourPassword");
        keyValueMap.put("age", "20");
        jedis.hmset(HASH_KEY, keyValueMap);

        //判断某个key是否在指定的Hash key中
        boolean existsUsernameKey = jedis.hexists(HASH_KEY, "username");
        System.out.println(existsUsernameKey);
        //从一个hash中获取某个field的值
        String value = jedis.hget(HASH_KEY, "username");
        System.out.println(value);

        //获取某个hash中键值对的数量，返回 3
        Long len = jedis.hlen(HASH_KEY);
        System.out.println(len);

       //获取一个hash中的所有key ，[password, username, age]
        Set<String> keys = jedis.hkeys(HASH_KEY);
        System.out.println(keys);

        //获取一个hash中的所有values，返回[yourUsername, YourPassword, 20]
        List<String> values = jedis.hvals(HASH_KEY);
        System.out.println(values);

        //获取指定hash的所有的键值对，{username=yourUsername, age=20, password=YourPassword}
        Map<String, String> allKVMap = jedis.hgetAll(HASH_KEY);
        System.out.println(allKVMap);

        List<String> multValues = jedis.hmget(HASH_KEY, "username", "password");
        // [yourUsername, YourPassword]
        System.out.println(multValues);
    }

}
