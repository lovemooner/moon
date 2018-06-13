package love.moon.reflect;

/**
 * Author: lovemooner
 * Date: 2018/6/9 13:55
 */
public class  ClassLoader100 {

    public String test() {
        return "test";
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Test.class.getClassLoader().loadClass("Test");

        Class c1 = Class.forName("love.moon.reflect.ClassLoader100");
        System.out.println(c1.getName());

        ClassLoader100 test = (ClassLoader100) c1.newInstance();

        System.out.println(test.test());

    }
}
