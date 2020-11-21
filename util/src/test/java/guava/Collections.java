package guava;

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Collections {


    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int index = s.length() - 1;
        while (index >= 0 &&s.charAt(index) == ' ') {
            index--;
            continue;
        }
        int len = 0;
        while (index >= 0 && s.charAt(index--) != ' ') {
            len++;
        }
        return len;
    }


    public static void main(String[] args) {
        String s = "Hello World  ";
        int r = lengthOfLastWord(s);
        System.out.println(r);
        // 普通Collection的创建
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

        // 不变Collection的创建
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");
    }
}
