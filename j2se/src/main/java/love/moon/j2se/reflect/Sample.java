package love.moon.j2se.reflect;

/**
 * Author: lovemooner
 * Date: 2017/5/4 16:55
 */
public class Sample {
    public int v1 = 1;
    public Sample(){
        System.out.println("Sample is load by :" + this.getClass().getClassLoader());
    }

    public String sayHello(){
        return "Hi loader";
    }

    public static void main(String[] args) {
        System.out.println("Hi");
    }
}
