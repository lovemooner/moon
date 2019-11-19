package love.moon.j2se.thread.concurrent.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMap100 {

    public static void main(String[] args) {
        Map<String,String> map=new ConcurrentHashMap();
        map.putIfAbsent("a","a");
        map.put("b","b");
        map.computeIfAbsent("a",key->map.putIfAbsent("a","a"));
    }
}
