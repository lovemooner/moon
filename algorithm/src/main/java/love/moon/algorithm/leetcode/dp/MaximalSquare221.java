package love.moon.algorithm.leetcode.dp;

/**
 * @author : ndong
 * date : 2021/2/16 23:20
 * desc :
 */
public class MaximalSquare221 {


    /**
     * 暴力求解
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] != '1') continue;
                maxSide = Math.max(maxSide, 1);//1 作为正方形的左上角
                // 计算可能的最大正方形边长
                int currentMaxSide = Math.min(rows - i, columns - j);
                for (int k = 1; k < currentMaxSide; k++) {
                    // 判断新增的一行一列是否均为 1
                    boolean flag = true;
                    if (matrix[i + k][j + k] == '0') {
                        break;
                    }
                    for (int m = 0; m < k; m++) {
                        if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        maxSide = Math.max(maxSide, k + 1);
                    } else {
                        break;
                    }
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    /**
     * 动态规划
     *
     * @param matrix
     * @return
     */
    public int maximalSquare1(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

}
