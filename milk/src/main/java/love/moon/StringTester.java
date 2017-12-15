package love.moon;

/**
 * Author: lovemooner
 * Date: 2017/12/13 16:53
 */
public class StringTester {

    public static void main(String[] args) {
        String a = "a";
        String param = new String("param" + a);
        String paramSame = param.intern();
        System.out.println(param == paramSame);
    }
}
