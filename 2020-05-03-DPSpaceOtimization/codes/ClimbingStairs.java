package codes;

class ClimbingStairs {
    public static void main(String[] args) {

    }

    public int climbStairs1(int n) {
        if (n < 2)
            return n;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    public int climbStairs(int n) {
        if (n < 2)
            return n;
        int dp_0 = 1, dp_1 = 2, dp_i = 0;
        for (int i = 2; i < n; i++) {
            dp_i = dp_1 + dp_0;
            dp_0 = dp_1;
            dp_1 = dp_i;
        }
        return dp_1;
    }
}