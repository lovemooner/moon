package love.moon;

/**
 * Author: lovemooner
 * Date: 2017/12/13 16:53
 */
public class StringTester {


    public void test(){
        int a;
        int b;
        String s;
    }

    public static void main(String[] args) {
        String a = "a";
        String param = new String("param" + a);
        String paramSame = param.intern();
        System.out.println(param == paramSame);
        StringTester t=new StringTester();
        t.test();
    }
}
