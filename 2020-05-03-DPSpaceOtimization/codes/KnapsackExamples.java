package codes;

import java.util.Arrays;

public class KanpsackExamples {
    public static void main(String[] args) {
        KanpsackExamples sol = new KanpsackExamples();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = sol.knapsackProfit3(profits, weights, 6);
        System.out.println("profit : " + maxProfit);
        int amount = 5;
        int[] coins = {1, 2, 5};
        System.out.println(sol.change3(amount, coins));
    }

    /*
    Definition :
    dp[i][j] : Max profit for capacity 'j' till index 'i'

    Transition function :
    dp[i][j] : Max(dp[i-1][j], profit[i] + dp[i - 1][j - weight[i]]

    Return :
    dp[n - 1][j]

    Base case :
    dp[i][0] = 0 for all 'i'
    dp[0][j] = profits[0] (as long as within weight capacity i.e. if weights[0] <= capacity

    */
    private int knapsackProfit(int[] profits, int[] weights, int capacity) {
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];

        // base case
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        // just one weight
        for (int j = 0; j <= capacity; j++) {
            if (weights[0] <= j) {
                dp[0][j] = profits[0];
            }
        }

        // recurrence
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= capacity; j++) {
                int profit1 = 0, profit2 = 0;
                // include
                if (weights[i] <= j)
                    profit1 = profits[i] + dp[i - 1][j - weights[i]];
                // exclude
                profit2 = dp[i - 1][j];

                dp[i][j] = Math.max(profit1, profit2);
            }

        }
        // result
        return dp[n - 1][capacity];
    }

    private int knapsackProfit2(int[] profits, int[] weights, int capacity) {
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length) {
            return 0;
        }

        int n = profits.length;
        int[][] dp = new int[2][capacity + 1];

        // just one weight
        for (int j = 0; j <= capacity; j++) {
            if (weights[0] <= j) {
                dp[0][j] = dp[1][j] = profits[0];
            }
        }

        // recurrence
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                int profit1 = 0, profit2 = 0;
                // include
                if (weights[i] <= j) {
                    profit1 = profits[i] + dp[(i - 1) % 2][j - weights[i]];
                }
                // exclude
                profit2 = dp[(i - 1) % 2][j];

                dp[i % 2][j] = Math.max(profit1, profit2);
            }
        }
        return dp[(n - 1) % 2][capacity];
    }

    private int knapsackProfit3(int[] profits, int[] weights, int capacity) {
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[] dp = new int[capacity + 1];

        // just one weight
        for (int j = 0; j <= capacity; j++) {
            if (weights[0] <= j) {
                dp[j] = profits[0];
            }
        }
        // recurrence
        for (int i = 1; i < n; i++) {
            for (int j = capacity; j >= 0; j--) {
                //for (int j = 0; j <= capacity; j++) {
                int profit1 = 0, profit2 = 0;
                // include
                if (weights[i] <= j) {
                    profit1 = profits[i] + dp[j - weights[i]];
                }
                // exclude
                profit2 = dp[j];

                dp[j] = Math.max(profit1, profit2);
            }
        }

        return dp[capacity];
    }

    //Unbounded Knapsack : https://leetcode.com/problems/coin-change-2/
    /*
    Definition :
    dp[i][j] : Total number of ways to make amount 'j' till index 'i' of coins array

    Transition function :
    dp[i][j] : dp[i-1][j] + dp[i][j - weight[i] (note that we have infinite supply)

    Return :
    dp[n - 1][amount]
     */
    public int change1(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // base case
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // recurrence
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (i > 0)
                    dp[i][j] = dp[i - 1][j];
                if (j >= coins[i])
                    dp[i][j] += dp[i][j - coins[i]];
            }
        }
        // result
        if (n == 0) {
            return amount == 0 ? 1 : 0;
        }
        return dp[n - 1][amount];
    }

    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[2][amount + 1];

        // base case
        dp[0][0] = dp[1][0] = 1;

        // recurrence
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (i > 0) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j];
                }
                if (j >= coins[i]) {
                    dp[i % 2][j] += dp[i % 2][j - coins[i]];
                }
            }
        }
        // result
        if (n == 0) {
            return amount == 0 ? 1 : 0;
        }
        return dp[(n - 1) % 2][amount];
    }

    public int change3(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int [amount + 1];

        // base case
        dp[0] = 1;

        // recurrence
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (i > 0) {
                    dp[j] = dp[j];
                }
                if (j >= coins[i]) {
                    dp[j] += dp[j - coins[i]];
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        // result
        if (n == 0) {
            return amount == 0 ? 1 : 0;
        }
        return dp[amount];
    }
}
