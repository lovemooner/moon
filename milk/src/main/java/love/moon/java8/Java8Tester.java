package love.moon.java8;

public class Java8Tester {

    final static String salutation = "Hello! ";

    public static void main(String args[]) {
        GreetingService greetService1 = msg -> {
            System.out.println(salutation + msg);
        };
        greetService1.sayMessage("Runoob");
    }

    interface GreetingService {
        void sayMessage(String message);
    }
}
