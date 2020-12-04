package love.moon.j2se.collection.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 由数组生成的list
 * @author : ndong
 * date : 2020/12/1 9:37
 * desc :
 */
public class ArrayList101 {

    @Test
    public void arrays() {
        String[] arrays = {"a", "b", "c"};
        List<String> list = Arrays.asList(arrays);
        list.add("d");
        list.remove("a");
    }
}
