package love.moon.jvm;

public class MaxMemory {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024/1024);;
    }
}
