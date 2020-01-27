package love.moon.j2se.exception;

/**
 * Author: lovemooner
 * Date: 2017/11/3 16:01
 */
public class ExceptionUtil {

    public static void raiseException(){
        throw createException();
    }

    public static RuntimeException createException(){
        return new RuntimeException("Ex");
    }
}
