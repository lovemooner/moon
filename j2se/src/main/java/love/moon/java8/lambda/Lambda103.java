package love.moon.java8.lambda;

/**
 * @author dongnan
 * @date 2020/5/15 10:02
 */
public class Lambda103 {

    @FunctionalInterface
    interface MyInterface1 {
        void test(String str);
    }

    public static void main(String args[]) {
        MyInterface1 interface1 = (str) -> System.out.println(str);
        interface1.test("test");
    }

}
