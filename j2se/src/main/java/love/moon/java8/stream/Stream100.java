package love.moon.java8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream100 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        strings.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

    }


    public static void testMap(){
        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        List list=strings.stream()
                .map(s->{
                    Map<String,String> map=new HashMap<>();
                    map.put(s,s);
                    return map;
                })
                .collect(Collectors.toList());

        System.out.println();
    }

    /**
     * List <==> Stream
     */
    public static void convert(){
        String[] str = {"Hello World", "Jiaming Chen", "Zhouhang Cheng"};
        Stream<String> stream = Stream.of(str);

        String[] strArray = stream.toArray(String[]::new);
        List<String> strList = stream.collect(Collectors.toList());
        ArrayList<String> strArrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        Set<String> strSet = stream.collect(Collectors.toSet());
    }

}
