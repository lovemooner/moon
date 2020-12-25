package love.moon.algorithm.dp;

import love.moon.util.Assert;

/**
 * @author : ndong
 * date : 2020/12/19 22:26
 * desc :
 */
public class ButtomUpCut {

    public static void main(String[] args) {
        int[] p = {0, 1, 5, 8, 9};
//        int r = buttom_up_cut(p);
        System.out.println(buttom_up_cut1(p,100));;
    }

    /**
     * p数组下标代表长度，从0开始，即0代表长度1
     * r数组下标同样代表长处，但从1开始，即1代表长度1
     *
     * @param p
     * @return
     */
    public static int buttom_up_cut(int[] p) {
        int[] r = new int[p.length];
        for (int i = 1; i < p.length; i++) { //i为钢材的长度
            int q = -1;
            for (int j = 1; j <= i; j++) {//j为左边的切割长度
                q = Math.max(q, p[j] + r[i - j]);
            }
            r[i] = q;
        }
        return r[p.length - 1];
    }

    public static int buttom_up_cut1(int[] p, int n) {
        int[] r = new int[n + 1];
        r[1] = p[1];
        for (int i = 1; i <= n; i++) {
            int q = -1;
            for (int j = 1; j <= i&&j<p.length; j++) {
                q = Math.max(q, p[j] + r[i - j]);
            }
            r[i] = q;
        }
        return r[n];
    }



}
