package love.moon.j2se.reflect.classloader;

/**
 * @author : ndong
 * date : 2020/11/19 10:34
 * desc :
 */
public class ClassLoader100 {

    public static void main(String[] args) {
        System.out.println(ClassReLoader.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
