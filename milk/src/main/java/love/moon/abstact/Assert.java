package love.moon.abstact;

/**
 * Author: lovemooner
 * Date: 2017/5/3 16:00
 */
public abstract class Assert {

    public abstract void method1();

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new NullPointerException(message);
        }
    }

    public static void main(String[] args) {
        Object obj=new Object();
        Assert.isNull(obj,"[Assertion failed] - the object argument must be null");
    }
}
