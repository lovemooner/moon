package love.moon.java8.lambda;

import java.util.Arrays;
import java.util.List;

public class LambdaFilter100 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream()
                .filter(n -> n % 2  == 1)
                 .forEach(System.out::println);
    }
}
