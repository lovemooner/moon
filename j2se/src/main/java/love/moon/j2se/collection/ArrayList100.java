package love.moon.j2se.collection;

import org.apache.tomcat.util.threads.LimitLatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2018/8/1 10:33
 */
public class ArrayList100 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");

        list.iterator().forEachRemaining(System.out::println);

        List<String> list2 = Collections.synchronizedList(new ArrayList<>());
        list2.add("a");
        list2.add("a");

        list2.stream();
    }
}
