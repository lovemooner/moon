package love.moon.j2se.keyWord;

/**
 * Author: lovemooner
 * Date: 2018/5/25 14:54
 */
public class FinalArray100 {

    public static void main(String[] args) {
        final int[] b = {1, 2};
        b[1]++;                //OK
//        b = new int[]{1, 2};    //NOT OK
        System.out.println(b[1]);


        final char[] arr1 = {'a', 'b'};
        arr1[1]='c';
        System.out.println(arr1[1]);
    }
}
