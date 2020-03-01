package love.moon.jvm;

import love.moon.jvm.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: lovemooner
 * Date: 2018/1/12 13:44
 */
public class HashMapOver {

    public static void main(String[] args) {
        Map<Person, Integer> map = new HashMap<Person, Integer>();
        Person p = new Person("lee", 12);
        map.put(p, 1);
        p.setName("dong"); 
        map.remove(p);
        System.out.println(map.size());
        p.setName("zhangsan");
        map.remove(p);
        System.out.println(map.size());

    }
}
