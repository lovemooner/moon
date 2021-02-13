package love.moon.algorithm.leetcode.dp;

import love.moon.util.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 322. 零钱兑换
 *
 * @author : moon
 * date : 2020/12/19 23:06
 * desc :
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int result = coinChange(coins, 11);
        Assert.assertEqual(result, 3);

    }

    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
