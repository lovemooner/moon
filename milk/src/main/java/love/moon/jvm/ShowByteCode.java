package love.moon.jvm;

/**
 * Author: lovemooner
 * Date: 2017/11/1 15:57
 */
public class ShowByteCode {

    public void test(String str){
        System.out.println(str);
    }

    public static void main(String[] args) {
        ShowByteCode instance=new ShowByteCode();
        String str="abc";
        instance.test(str);
    }
}