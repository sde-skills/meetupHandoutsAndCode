package stocksProblems;

import java.util.Arrays;

public class LeetCode121 {
    public static void main(String[] args) {
        LeetCode121 sol = new LeetCode121();
        int[] prices = {7, 1, 5, 3, 6, 4};
        //int[] prices = {7, 6, 4, 3, 1};
        System.out.println(sol.maxProfit3(prices));
    }

   /*
       dp[i][k][0] - max profit till day 'i', with at most 'k' transactions, when we have '0' stocks in hand after last operation (sell or no-op)
       dp[i][k][1] - max profit till day 'i', with at most 'k' transactions, when we have '1' stocks in hand after last operation (buy or no-op)

       Transition function :
       dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
       dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

       Result :
       dp[n][1][0]

       Base case :
       dp[0][k][0] = 0, dp[0][k][1] = -Infinity
       dp[i][0][0] = 0, dp[i][0][1] = -Infinity

    */

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][2];

        //Base case
        for (int i = 0; i < n + 1; i++) {
            for (int k = 0; k <= 1; k++) {
                if (i == 0) {
                    dp[i][k][1] = Integer.MIN_VALUE;
                }
                if (k == 0) {
                    dp[i][k][1] = Integer.MIN_VALUE;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int k = 0; k <= 1; k++) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i - 1]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], k >= 1 ? dp[i - 1][k - 1][0] - prices[i - 1] : Integer.MIN_VALUE);
            }
        }

        return dp[n][1][0];
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][2];

        //Base case
        for (int i = 0; i < n + 1; i++) {
            if (i == 0) {
                dp[i][0][1] = Integer.MIN_VALUE;
                dp[i][1][1] = Integer.MIN_VALUE;
            }
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < n + 1; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i - 1]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i - 1]);
        }

        return dp[n][1][0];
    }

    public int maxProfit3(int[] prices) {
        int n = prices.length;
        //Base case
        int dp_i00 = 0, dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;

        for (int i = 1; i < n + 1; i++) {
            dp_i10 = Math.max(dp_i10, dp_i11 + prices[i - 1]);
            dp_i11 = Math.max(dp_i11, dp_i00 - prices[i - 1]);
        }

        return dp_i10;
    }


    // Other solutions
    public int maxProfit4(int prices[]) {
        int res = 0, n = prices.length;
        int minSoFar = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (prices[i] < minSoFar)
                minSoFar = prices[i];
            else if (prices[i] - minSoFar > res)
                res = prices[i] - minSoFar;
        }
        return res;
    }

    // convert to kadane's - max subarray problem
    public int maxProfit5(int[] prices) {
        int maxCur = 0, maxSoFar = 0, n = prices.length;
        for (int i = 1; i < n; i++) {
            maxCur += prices[i] - prices[i - 1];
            maxCur = Math.max(0, maxCur);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    // divide/conquer
    public int maxProfit6(int[] prices) {
        int n = prices.length;
        if (prices.length == 0)
            return 0;
        return helper(prices, 0, n - 1)[0];
    }

    //index 0 -> max profit, index 1 -> max price, index 2 -> min price in the range
    private int[] helper(int[] prices, int i, int j) {
        // base case
        int[] res = {0, Integer.MIN_VALUE, Integer.MAX_VALUE};
        if (i == j) {
            res[1] = res[2] = prices[i];
            return res;
        }
        int m = i + (j - i) / 2;
        int[] left = helper(prices, i, m); // left half
        int[] right = helper(prices, m + 1, j); // right half
        res[0] = Math.max(left[0], Math.max(right[0], right[1] - left[2]));
        res[1] = Math.max(left[1], right[1]);
        res[2] = Math.min(left[2], right[2]);
        return res;
    }

}
