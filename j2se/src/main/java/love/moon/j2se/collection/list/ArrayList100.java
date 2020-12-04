package love.moon.j2se.collection.list;

import org.apache.tomcat.util.threads.LimitLatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2018/8/1 10:33
 */
public class ArrayList100 {

    public void test1(){
        List list = new ArrayList();
        list.add("a");

        list.iterator().forEachRemaining(System.out::println);

        List<String> list2 = Collections.synchronizedList(new ArrayList<String>());
        list2.add("a");
        list2.get(0);

        list2.stream();
    }

    public void testRemove(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> ite=list.iterator();
        while (ite.hasNext()) {
            String e=ite.next();
            if (e.equals("a")) {
                list.remove(e);
            }
        }
    }


    public static void main(String[] args) {
        ArrayList100 list100=new ArrayList100();
        list100.testRemove();
    }
}
