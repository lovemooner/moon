package love.moon.j2se.keyWord;

/**
 * Author: lovemooner
 * Date: 2018/5/22 13:34
 */
public class String101 {

    public static void main(String[] args) {
//        String s1 = new StringBuilder().append("String").append("Test").toString();
//        s1.intern();
//        String s2 = "StringTest";
//        System.out.println(s1==s2);
        String s1=new String("StringTest");
        String s2 = "StringTest";
        s1.intern();
        System.out.println(s2 == s1);
//        System.out.println(s1.intern() == s1);
//
//        String s2 = new StringBuilder().append("ja").append("va").toString();
//        System.out.println(s2.intern() == s2);
//
//        String s1 = new String("1");
//        s1.intern();
//        String s2 = "1";
//
//        System.out.println(s1 == s2);
//
//        String s3 = new String("1") + new String("1");
//        s3.intern();
//        String s4 = "11";
//
//        System.out.println(s3 == s4);

    }
}
