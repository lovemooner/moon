package love.moon.algorithm.leetcode.dp;

/**
 *   斐波拉契数列Fibonacci
 *   Fibonacci (n) = 0; n = 0
 *   Fibonacci (n) = 1; n = 1
 *   Fibonacci (n) = Fibonacci(n-1) + Fibonacci(n-2)
 *
 * @author : moon
 * date : 2020/12/19 23:19
 * desc :
 */
public class Fibonacci {

    /**
     * 递归
     * @param n
     * @return
     */
    public int fib1(int n) {
        if(n==0||n==1) return n;
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 自底向上的动态规划
     * @param n
     * @return
     */
    public  int fib2(int n) {
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
    public  int fib3(int n) {
        if (n <= 0) return n;
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++){
            memo[i] = -1;
        }
        return upDown(n, memo);
    }

    public  int upDown(int n, int[] memo) {
        if (memo[n] != -1){
            return memo[n];
        }
        if (n <= 2) {
            memo[n] = 1;
        } else {
            memo[n] = upDown(n - 1, memo) + upDown(n - 2, memo);
        }
        return memo[n];
    }

    public static void main(String[] args) {
        Fibonacci f= new Fibonacci();
        int result=f.fib3(6);
        System.out.println(result);
    }

}
