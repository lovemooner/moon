package love.moon.algorithm.leetcode.dp;

/**
 * @author : ndong
 * date : 2020/11/26 16:48
 * desc :
 */
public class UniquePaths62 {

    /**
     * m*n n:行,m:列
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int n,int m) {
        int[][] dp=new int[n][m];
        for(int i=0;i<m;i++) dp[0][i]=1;
        for(int i=0;i<n;i++) dp[i][0]=1;
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }

    public static void main(String[] args) {
        int result = uniquePaths(7, 3);
        System.out.println(result);
    }
}
