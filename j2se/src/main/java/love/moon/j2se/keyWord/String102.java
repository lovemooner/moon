package love.moon.j2se.keyWord;

/**
 * Author: lovemooner
 * Date: 2018/6/2 12:29
 */
public class String102 {

    public static void main(String[] args) {
        String s = new String("1");
        String tmp = s.intern();
        String s2 = "1";
        System.out.println(s == s2);
        String s3 = new String("1") + new String("1");
        String tmp2 = s3.intern();
        ;
    }
}
