package stocksProblems;

public class LeetCode309 {
    public static void main(String[] args) {
        LeetCode309 sol = new LeetCode309();
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(sol.maxProfit(prices));
    }

   /*
       dp[i][0] - max profit till day 'i', when we have '0' stocks in hand after last operation (sell or no-op)
       dp[i][1] - max profit till day 'i', when we have '1' stocks in hand after last operation (buy or no-op)

       Transition function :
       dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
       dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
       (with 'cooldown' we can't buy on i'th day if stock was sold on (i-1)'th day)

       Result :
       dp[n][0]

       Base case :
       dp[0][0] = 0, dp[0][1] = -Infinity
       dp[i][1] = -Infinity

   */

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        //Base case
        for (int i = 0; i < n + 1; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], i >= 2 ? dp[i - 2][0] - prices[i - 1] : -prices[i - 1]);
        }

        return dp[n][0];
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        //Base case
        int dp_i1 = Integer.MIN_VALUE, dp_i0 = 0, dp_i0_pre = 0;

        for (int i = 1; i < n + 1; i++) {
            int dp_i0_old = dp_i0;
            dp_i0 = Math.max(dp_i0, dp_i1 + prices[i - 1]);
            dp_i1 = Math.max(dp_i1, dp_i0_pre - prices[i - 1]);
            dp_i0_pre = dp_i0_old;
        }

        return dp_i0;
    }
}
