package love.moon.java8.lambda;

public class Lambda100 {

    public static void main(String[] args) {
        MyInterface interface2 = msg ->   System.out.println(msg);
        interface2.test("Runoob");
    }

    @FunctionalInterface
    interface MyInterface {
        void test(String msg);
    }
}
