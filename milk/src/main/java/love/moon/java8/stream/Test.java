package love.moon.java8.stream;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
    }
}
