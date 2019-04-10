package love.moon.java8.lambda;

import java.lang.FunctionalInterface;

public class Lambda100 {

    public static void main(String args[]) {
        call((str)-> {
            System.out.println(str);
            System.out.println(str);}
            );

    }

    private static void call(MyInterface1 interface1){
        interface1.test("test");
    }


    @FunctionalInterface
    interface MyInterface1 {
         void test(String str);
    }


}
