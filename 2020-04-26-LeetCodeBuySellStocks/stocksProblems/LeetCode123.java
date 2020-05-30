package stocksProblems;

public class LeetCode123 {
    public static void main(String[] args) {
        LeetCode123 sol = new LeetCode123();
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(sol.maxProfit(prices));
    }

       /*
       dp[i][k][0] - max profit till day 'i', with at most 'k' transactions, when we have '0' stocks in hand after last operation (sell or no-op)
       dp[i][k][1] - max profit till day 'i', with at most 'k' transactions, when we have '1' stocks in hand after last operation (buy or no-op)

       Transition function :
       dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
       dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

       Result :
       dp[n][2][0]

       Base case :
       dp[0][k][0] = 0, dp[0][k][1] = -Infinity
       dp[i][0][0] = 0, dp[i][0][1] = -Infinity

    */

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][3][2];

        //Base case
        for (int i = 0; i < n + 1; i++) {
            for (int k = 0; k <= 2; k++) {
                if (i == 0) {
                    dp[i][k][1] = Integer.MIN_VALUE;
                }
                if (k == 0) {
                    dp[i][k][1] = Integer.MIN_VALUE;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i - 1]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], k >= 1 ? dp[i - 1][k - 1][0] - prices[i - 1] : Integer.MIN_VALUE);
            }
        }

        return dp[n][2][0];
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][3][2];

        //Base case
        for (int i = 0; i < n + 1; i++) {
            if (i == 0) {
                dp[i][0][1] = Integer.MIN_VALUE;
                dp[i][1][1] = Integer.MIN_VALUE;
                dp[i][2][1] = Integer.MIN_VALUE;
            }
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < n + 1; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i - 1]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i - 1]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i - 1]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i - 1]);
        }

        return dp[n][2][0];
    }

    public int maxProfit(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        int dp_i00 = 0;
        for (int i = 0; i < prices.length; i++) {
            dp_i10 = Math.max(dp_i10, dp_i11 + prices[i]);
            dp_i11 = Math.max(dp_i11, dp_i00 - prices[i]);
            dp_i20 = Math.max(dp_i20, dp_i21 + prices[i]);
            dp_i21 = Math.max(dp_i21, dp_i10 - prices[i]);
        }

        return dp_i20;
    }

}
