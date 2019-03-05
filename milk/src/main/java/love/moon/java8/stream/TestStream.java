package love.moon.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

    public static void convert(){
        String[] str = {"Hello World", "Jiaming Chen", "Zhouhang Cheng"};
        Stream<String> stream = Stream.of(str);

        String[] strArray = stream.toArray(String[]::new);
        List<String> strList = stream.collect(Collectors.toList());
        ArrayList<String> strArrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        Set<String> strSet = stream.collect(Collectors.toSet());
    }

    public static void createStream() {
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
       // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
       // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();
    }


    public static void main(String[] args) {

        createStream();
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty())
                .collect(Collectors.toList());


    }
}
