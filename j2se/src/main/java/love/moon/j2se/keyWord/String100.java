package love.moon.j2se.keyWord;

/**
 * Author: lovemooner
 * Date: 2017/12/13 16:53
 */
public class String100 {


    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "a";
        System.out.println(str1 == str2);//true

        String str3 = str1;
        System.out.println(str1 == str3);//true

        String str4 = new String("a");
        String str5 = str4;
        System.out.println(str4 == str5);//true

        System.out.println(str1 == str4);//false
    }
}



