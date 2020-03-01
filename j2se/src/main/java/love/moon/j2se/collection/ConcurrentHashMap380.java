package love.moon.j2se.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 把整个Map分为N个Segment（类似HashTable），可以提供相同的线程安全，但是效率提升N倍，默认提升16倍。
 * Author: lovemooner
 * Date: 2017/6/14 16:58
 */
public class ConcurrentHashMap380 {

    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
    public static void main(String[] args) {
        new Thread("Thread1"){
            @Override
            public void run() {
                map.put(3, 33);
            }
        };

        new Thread("Thread2"){
            @Override
            public void run() {
                map.put(4, 44);
            }
        };

        new Thread("Thread3"){
            @Override
            public void run() {
                map.put(7, 77);
            }
        };
        System.out.println(map);
    }
}