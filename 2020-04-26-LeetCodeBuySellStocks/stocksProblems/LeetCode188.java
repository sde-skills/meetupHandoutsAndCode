package stocksProblems;

import java.util.Arrays;

public class LeetCode188 {
    public static void main(String[] args) {
        LeetCode188 sol = new LeetCode188();
        int[] prices = {3, 2, 6, 5, 0, 3};
        int k = 2;
        System.out.println(sol.maxProfit(2, prices));
    }

   /*
       dp[i][k][0] - max profit till day 'i', with at most 'k' transactions, when we have '0' stocks in hand after last operation (sell or no-op)
       dp[i][k][1] - max profit till day 'i', with at most 'k' transactions, when we have '1' stocks in hand after last operation (buy or no-op)

       Transition function :
       dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
       dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

       Result :
       dp[n][k][0]

       Base case :
       dp[0][k][0] = 0, dp[0][k][1] = -Infinity
       dp[i][0][0] = 0, dp[i][0][1] = -Infinity

    */

    public int maxProfit1(int K, int[] prices) {
        int n = prices.length;
        //optimization - still leads to memory exceeded exception
        if (K > n/2) {
            K = n/2;
        }
        int[][][] dp = new int[n + 1][K + 1][2];

        //Base case
        for (int i = 0; i < n + 1; i++) {
            for (int k = 0; k <= K; k++) {
                if (i == 0) {
                    dp[i][k][1] = Integer.MIN_VALUE;
                }
                if (k == 0) {
                    dp[i][k][1] = Integer.MIN_VALUE;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int k = 1; k <= K; k++) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i - 1]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], k >= 1 ? dp[i - 1][k - 1][0] - prices[i - 1] : Integer.MIN_VALUE);
            }
        }

        return dp[n][K][0];
    }

    public int maxProfit2(int K, int[] prices) {
        int n = prices.length;
        //optimization : TODO : since K is infinity can we just ignore it in our recurrence relation?
        if (K > n/2) {
            K = n/2;
        }
        int[] dp_ik0 = new int[K + 1];
        int[] dp_ik1 = new int[K + 1];
        Arrays.fill(dp_ik1, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            for (int k = 1; k <= K; k++) {
                dp_ik0[k] = Math.max(dp_ik0[k], dp_ik1[k] + prices[i]);
                dp_ik1[k] = Math.max(dp_ik1[k], dp_ik0[k - 1] - prices[i]);
            }
        }

        return dp_ik0[K];
    }

    @SuppressWarnings("Duplicates")
    public int maxProfit(int K, int[] prices) {
        int n = prices.length;
        //optimization, if k > n/2 then value of 'k' doesn't matter
        if (K > n/2) {
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


        int[] dp_ik0 = new int[K + 1];
        int[] dp_ik1 = new int[K + 1];
        Arrays.fill(dp_ik1, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            for (int k = 1; k <= K; k++) {
                dp_ik0[k] = Math.max(dp_ik0[k], dp_ik1[k] + prices[i]);
                dp_ik1[k] = Math.max(dp_ik1[k], dp_ik0[k - 1] - prices[i]);
            }
        }

        return dp_ik0[K];
    }

}
