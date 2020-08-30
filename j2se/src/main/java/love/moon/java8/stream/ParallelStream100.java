package love.moon.java8.stream;

import love.moon.design.other.mvc.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelStream100 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(System.out::println);
    }

    /**
     * 数据流式处理，非一个函数执行结束再执行下一个函数
     */
    @Test
    public void handOrder() {
        List<Student> list = getStudentList();
        list.parallelStream().filter(e -> {
            System.out.println("thread:"+Thread.currentThread().getName() +" filter " + e.getName());
            return e.getName() != null;
        }).map(e -> {
            System.out.println("thread:"+Thread.currentThread().getName() +" map " + e.getName());
            return e.getName();
        }).forEach(e -> System.out.println("thread:"+Thread.currentThread().getName() +" foreach " + e));
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
        Student student3 = new Student();
        student3.setName("a3");
        student3.setScore(30);
        list.add(student3);
        return list;
    }
}
