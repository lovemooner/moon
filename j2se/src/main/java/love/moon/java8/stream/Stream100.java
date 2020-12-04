package love.moon.java8.stream;

import love.moon.design.other.mvc.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author
 */

public class Stream100 {

    public static void main(String[] args) {
        Stream100 stream100 = new Stream100();
        stream100.demo();
    }


    public void demo() {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        strings.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    public void map() {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        List list1 = strings.stream()
                .map(s -> {
                    Map<String, String> map = new HashMap<>(1);
                    map.put(s, s);
                    return map;
                })
                .collect(Collectors.toList());
        //demo2
        List<Student> list2 = getStudentList();
        List<String> names = list2.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(names);
        //使用map方法获取list数据中的name的长度
        List<Integer> length = list2.stream().map(Student::getName)
                .map(String::length).collect(Collectors.toList());
        System.out.println(length);
        //将每人的分数-10
        List<Integer> score = list2.stream().map(Student::getScore).map(i -> i - 10).collect(Collectors.toList());
        System.out.println(score);
    }


    public void mapReduce() {
        //初始化List数据同上
        List<Student> list = getStudentList();
        //计算学生总分
        Integer totalScore1 = list.stream().map(Student::getScore).reduce(0, (a, b) -> a + b);
        System.out.println(totalScore1);
        //计算学生总分，返回Optional类型的数据，改类型是java8中新增的，主要用来避免空指针异常
        Optional<Integer> totalScore2 = list.stream().map(Student::getScore).reduce((a, b) -> a + b);
        System.out.println(totalScore2.get());
        //计算最高分和最低分
        Optional<Integer> max = list.stream().map(Student::getScore).reduce(Integer::max);
        Optional<Integer> min = list.stream().map(Student::getScore).reduce(Integer::min);
        System.out.println(max.get());
        System.out.println(min.get());
    }


    List<Student> getStudentList() {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("a1");
        student1.setScore(10);
        list.add(student1);
        Student student2 = new Student();
        student2.setName("a2");
        student2.setScore(20);
        list.add(student2);
        return list;
    }

    /**
     * List <==> Stream
     */
    public static void convert() {
        //array --> stream
        String[] str = {"Hello World", "Jiaming Chen", "Zhouhang Cheng"};
        Stream<String> stream = Stream.of(str);
        //stream --> array
        String[] strArray = stream.toArray(String[]::new);
        List<String> strList = stream.collect(Collectors.toList());
        ArrayList<String> strArrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        Set<String> strSet = stream.collect(Collectors.toSet());
    }

}
