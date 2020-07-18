package love.moon.j2se.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dongnan
 * @date 2020/6/18 10:11
 */
public class Iterator100 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.iterator();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
