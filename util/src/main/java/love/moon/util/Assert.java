package love.moon.util;

/**
 * @author : ndong
 * date : 2020/11/20 15:44
 * desc :
 */
public class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new RuntimeException(message);
        }
    }

    public static void assertTrue(Boolean flag) {
        if (!Boolean.TRUE.equals(flag)) {
            throw new RuntimeException("flag:false,expect:true");
        }
    }

    public static void assertFalse(Boolean flag) {
        if (!Boolean.FALSE.equals(flag)) {
            throw new RuntimeException("flag:true,expect:false");
        }
    }

    public static void assertEqual(int source,int expect) {
        if(source!=expect){
            throw new RuntimeException("source:"+source+",expect:"+expect);
        }
    }
}
