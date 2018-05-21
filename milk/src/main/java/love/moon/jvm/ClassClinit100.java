package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2018/5/21 14:56
 */
public class ClassClinit100 {

    public static String str1 = "str";
    private static Example example1 = new Example();//这里会加载Example执行clinit，并执行<init>生成对象实例
    private Example example2 = new Example();//非静态field会加入到构造器做初始化

    public static void main(String[] args) {
        String b = ClassClinit100.str1;
    }

    static {
        System.out.println("hi");
    }


}
