package love.moon.algorithm.dp;

/**
 *   斐波拉契数列Fibonacci
 *   Fibonacci (n) = 0; n = 0
 *   Fibonacci (n) = 1; n = 1
 *   Fibonacci (n) = Fibonacci(n-1) + Fibonacci(n-2)
 *
 *
 * @author : moon
 * date : 2020/12/19 23:19
 * desc :
 */
public class Fibonacci {

    //递归
    public int fib(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    //自底向上的动态规划
    public static int fibButtomUp(int n) {
        if (n <= 0) return n;
        int[] cached = new int[n + 1];
        cached[0] = 0;
        cached[1] = 1;
        for (int i = 2; i <= n; i++) {
            cached[i] = cached[i - 1] + cached[i - 2];
        }
        return cached[n];
    }


    /**
     * 自顶向下的备忘录法
     *
     * @param n
     * @return
     */
    public static int Fibonacci(int n) {
        if (n <= 0)
            return n;
        int[] Memo = new int[n + 1];
        for (int i = 0; i <= n; i++)
            Memo[i] = -1;
        return upDown(n, Memo);
    }

    public static int upDown(int n, int[] Memo) {
        if (Memo[n] != -1)
            return Memo[n];
        //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在Memo备忘录中。
        if (n <= 2) {
            Memo[n] = 1;
        } else {
            Memo[n] = upDown(n - 1, Memo) + upDown(n - 2, Memo);
        }
        return Memo[n];
    }
}
