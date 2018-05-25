package love.moon.keyWord;

/**
 * Author: lovemooner
 * Date: 2018/5/22 10:38
 */
public class Integer100 {

    public static void main(String[] args) {
        Integer a=10; //装箱
        Integer b=new Integer(10);
        System.out.println(a==b); //false

        int c=new Integer(10);//拆箱
        int d=10;
        System.out.println(c==d);
    }
}
