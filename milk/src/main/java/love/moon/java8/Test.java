package love.moon.java8;

public class Test {



    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("In Java8, Lambda expression rocks !!");
            }
        }).start();

        new Thread(()-> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }
}
