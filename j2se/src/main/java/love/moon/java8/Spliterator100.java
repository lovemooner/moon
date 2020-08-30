package love.moon.java8;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.stream.IntStream;

/**
 * @author dongnan
 * @date 2020/8/25 17:22
 */
public class Spliterator100 {

    /**
     * Spliterator是将一个stream进行对半平分的操作类
     * Arrays.parallelSetAll 和 IntStream.range可以生成一个指定长度Int的Stream
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        System.out.println("------print test------");
        int[] array = new int[10];
        Arrays.parallelSetAll(array, i -> i);
        // 这个方法也可以构建一个Stream，然后再构建Spliterator
        Spliterator.OfInt test = IntStream.range(0, 10).spliterator();
        test.forEachRemaining((int value) -> {
            System.out.println(Thread.currentThread().getName() + "--" + value);
        });

        Spliterator.OfInt sp = Arrays.spliterator(array);
        // 分割完后sp还剩5个元素，sp1也是5个元素
        Spliterator.OfInt sp1 = sp.trySplit();
        // 分割完后sp1为3个，sp2为2个
        Spliterator.OfInt sp2 = sp1.trySplit();

        System.out.println("------print sp1------");
        sp1.forEachRemaining((int value) -> {
            System.out.println(Thread.currentThread().getName() + "--" + value);
        });
        System.out.println("-----print sp2-------");
        sp2.forEachRemaining((int value) -> {
            System.out.println(Thread.currentThread().getName() + "--" + value);
        });
        System.out.println("------print sp------");
        sp.forEachRemaining((int value) -> {
            System.out.println(Thread.currentThread().getName() + "--" + value);
        });
    }
}
