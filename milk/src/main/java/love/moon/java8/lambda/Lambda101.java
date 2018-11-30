package love.moon.java8.lambda;

import java.lang.FunctionalInterface;

public class Lambda101 {

    private static void doTest(MyInterface1 interface1){
        interface1.test();
    }


    public static void main(String args[]) {
        doTest(()-> System.out.println("from fun"));

    }

    @FunctionalInterface
    interface MyInterface1 {
         void test();
    }


}
