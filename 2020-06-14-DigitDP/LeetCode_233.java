//Ref : https://leetcode.com/problems/number-of-digit-one/
class Solution {
    int[][][] dp = new int[12][2][12];
    String num;

    public int countDigitOne(int n) {
        num = String.valueOf(n);
        for (int[][] x : dp) {
            for (int[] y : x) {
                Arrays.fill(y, -1);
            }
        }

        return solve(0, 1, 0);
    }

    private int solve(int pos, int restricted, int sum) {
        if (pos >= num.length()) {
            return sum;
        }
        if (dp[pos][restricted][sum] != -1) {
            return dp[pos][restricted][sum];
        }
        int limit = (restricted > 0) ? num.charAt(pos) - '0' : 9;
        int res = 0;

        for (int i = 0; i <= limit; i++) {
            int newRestricted = restricted;
            if (i < limit) {
                newRestricted = 0;
            }
            if (i == 1) {
                res += solve(pos + 1, newRestricted, sum + 1);
            } else {
                res += solve(pos + 1, newRestricted, sum);
            }
        }
        return dp[pos][restricted][sum] = res;
    }
}