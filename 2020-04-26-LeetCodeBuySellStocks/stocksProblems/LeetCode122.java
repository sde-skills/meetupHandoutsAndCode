package stocksProblems;

public class LeetCode122 {
    public static void main(String[] args) {
        LeetCode122 sol = new LeetCode122();
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(sol.maxProfit(prices));
    }


   /*
       dp[i][0] - max profit till day 'i', when we have '0' stocks in hand after last operation (sell or no-op)
       dp[i][1] - max profit till day 'i', when we have '1' stocks in hand after last operation (buy or no-op)

       Transition function :
       dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
       dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])

       Result :
       dp[n][0]

       Base case :
       dp[0][0] = 0, dp[0][1] = -Infinity
       dp[i][1] = -Infinity

    */

    @SuppressWarnings("Duplicates")
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        //Base case
        for (int i = 0; i < n + 1; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
        }

        return dp[n][0];
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        //Base case
        int dp_i1 = Integer.MIN_VALUE, dp_i0 = 0;

        for (int i = 1; i < n + 1; i++) {
            dp_i0 = Math.max(dp_i0, dp_i1 + prices[i - 1]);
            dp_i1 = Math.max(dp_i1, dp_i0 - prices[i - 1]);
        }

        return dp_i0;
    }

    // Other simple solution
    // Just make profit whenever we can
    public int maxProfit3(int[] prices) {
        int res = 0, n = prices.length;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1])
                res += prices[i] - prices[i - 1];
        }
        return res;
    }
}
