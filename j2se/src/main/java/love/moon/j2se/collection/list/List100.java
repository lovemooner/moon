package love.moon.j2se.collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2018/7/17 13:46
 */
public class List100 {

    @Test
    public void testClear(){
        List<String> list=new ArrayList();
        list.add("test");
        list.clear();
        list.forEach(System.out::println);
    }

    public void  test(){
        ArrayList list=new ArrayList();
        Elem elem1=new Elem();
        elem1.setName("elem1");
        Elem elem2=elem1;
        elem2.setName("elem2");
        elem1=null;
        System.out.println("end");
    }


    class Elem{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
