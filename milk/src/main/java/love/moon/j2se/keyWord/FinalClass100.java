package love.moon.j2se.keyWord;

/**
 * Author: lovemooner
 * Date: 2018/5/25 14:28
 */
public final class FinalClass100 {
    public static int a;
    public int b;


    public final void  test(){}

    private int test2(){int a=1;return a;}

    public static void main(String[] args) {
        FinalClass100 s=new FinalClass100();
        System.out.println(s.test2());
    }


}
