package love.moon.algorithm.leetcode;

import love.moon.util.Assert;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
 *
 * @author : ndong
 * date : 2021/2/12 1:53
 * desc :
 */
public class MaxProfit121 {

    public static void main(String[] args) {
        int prices[] ={1,2};
        MaxProfit121 sol=new MaxProfit121();
        Assert.assertEqual(sol.maxProfit(prices),1);
    }

    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i]<minprice){
                minprice=prices[i];
            }
            int profit = prices[i] - minprice;
            if (profit > maxprofit) {
                maxprofit = profit;
            }
        }
        return maxprofit;
    }
}
