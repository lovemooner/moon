package love.moon.java8.lambda;

public class LambdaThread100 {

    public static void main(String[] args) {

        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!") ).start();


    }
}
