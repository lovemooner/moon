package love.moon.java8;

import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterface {

    public static void main(String[] argv) {
        Processor stringProcessor = (String str) -> str.length();
        String name = "Java Lambda";
        int length = stringProcessor.getStringLength(name);
        System.out.println(length);
       //IntFunction
        IntFunction function= (x) -> x+1;
        System.out.println(function.apply(1));
       //Predicate
        Predicate<String> i  = (s)-> s.length() > 5;
        System.out.println(i.test("abc "));

        Supplier<Integer> supplier  = () ->  "www.w3cschool.cn".length();
        System.out.println(supplier.get());


        Supplier<Integer> supplier1  = "www.w3cschool.cn"::length;
        System.out.println(supplier1.get());

    }

//    @FunctionalInterface
    interface Processor {
        int getStringLength(String str);
    }
}
