package love.moon.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2018/7/17 13:46
 */
public class List100 {

    public void  test(){
        ArrayList list=new ArrayList();
        Elem elem1=new Elem();
        elem1.setName("elem1");
        Elem elem2=elem1;
        elem2.setName("elem2");
        elem1=null;
        System.out.println("end");
    }

    public static void main(String[] args) {
        List100 list100=new List100();
        list100.test();

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
