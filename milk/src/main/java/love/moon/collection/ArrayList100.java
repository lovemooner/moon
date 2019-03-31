package love.moon.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2018/8/1 10:33
 */
public class ArrayList100 {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("a");

            List<String> list2 = Collections.synchronizedList(new ArrayList<String>());
            list2.add("a");
            list2.add("a");
    }
}
