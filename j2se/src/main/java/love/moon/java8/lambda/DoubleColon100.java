package love.moon.java8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author dongnan
 * @date 2020/5/14 11:24
 */
public class DoubleColon100 {

    public static void printValur(String str) {
        System.out.println("print value : " + str);
    }

    public static void printValur1(String str) {
        System.out.println("print value : " + str);
    }

    public static void main(String[] args) {
        List<String> a1 = Arrays.asList("a", "b", "c");
        a1.forEach(x -> DoubleColon100.printValur(x));
        a1.forEach(DoubleColon100::printValur);
        a1.forEach(DoubleColon100::printValur1);
    }


}
