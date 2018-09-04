package love.moon.util;

import java.util.Random;

/**
 * Author: lovemooner
 * Date: 2017/6/23 16:32
 */
public class RandomUtil {

  private static   String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String randomChars(int len) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(random.nextInt(52)));
        }
        return sb.toString();
    }

    public static String random(){
        return randomChars(5);
    }

    public static int randomInt(int n){
        Random random = new Random();
        return random.nextInt(n);
    }
}
