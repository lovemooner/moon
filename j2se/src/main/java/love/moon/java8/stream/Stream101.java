package love.moon.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * test the stream principle
 */
public class Stream101 {


    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        strings.stream()
                .filter(s -> {
                    System.out.println("filter "+s);
                    return s.startsWith("c");
                }).map(s -> {
                    System.out.println("map "+s);
                    return s.toUpperCase();
                 }).forEach(s->{
                     System.out.println("foreach "+s);
                 });

    }

    /**
     * List <==> Stream
     */
    public static void convert() {
        String[] str = {"Hello World", "Jiaming Chen", "Zhouhang Cheng"};
        Stream<String> stream = Stream.of(str);

        String[] strArray = stream.toArray(String[]::new);
        List<String> strList = stream.collect(Collectors.toList());
        ArrayList<String> strArrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        Set<String> strSet = stream.collect(Collectors.toSet());
    }

}
