package love.moon.java8.lambda;

import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaBuildIn200 {

    public static void main(String[] argv) {
        //IntFunction
        IntFunction function = (x) -> x + 1;
        System.out.println(function.apply(1));
        //Predicate
        Predicate<String> i = (s) -> s.length() > 5;
        System.out.println(i.test("abc "));
        //Supplier
        Supplier<Integer> supplier = () -> "www.w3cschool.cn".length();
        System.out.println(supplier.get());

    }

}
