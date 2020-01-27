package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2018/5/23 16:22
 */
public class ClassClinit102 {

    private static Example example1 = new Example();
    private Example example2 = new Example();
    private int b=2;
    private Integer c=3;

    public ClassClinit102() {
        int a = 1;
        Example example3=new Example();
    }

    public static void main(String[] args) {
        System.out.println(ClassClinit102.example1);
        ;
    }
}
