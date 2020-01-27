package love.moon.j2se.reflect;

/**
 * Author: lovemooner
 * Date: 2017/5/5 14:11
 */
public class ClassLoaderTree {

    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTree.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }
}
