package love.moon.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaMap100 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> mapped = numbers.stream().map(x -> x * 2).collect(Collectors.toList());
        mapped.forEach(System.out::println);
    }
}
