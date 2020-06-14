//Ref : https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/
class Solution {
    int[][][] dp = new int[33][2][2];
    String number;

    public int findIntegers(int num) {
        number = Integer.toBinaryString(num);
        for (int[][] x : dp) {
            for (int[] y : x) {
                Arrays.fill(y, -1);
            }
        }
        return solve(0, 1, 0);
    }

    private int solve(int pos, int restricted, int prev) {
        if (pos == number.length()) {
            return dp[pos][restricted][prev] = 1;
        }
        if (dp[pos][restricted][prev] != -1) {
            return dp[pos][restricted][prev];
        }

        int limit = (restricted > 0) ? number.charAt(pos) - '0' : 1;
        int res = 0;

        for (int i = 0; i <= limit; i++) {
            int newRestricted = restricted;
            if (i < limit) {
                newRestricted = 0;
            }
            if (prev != 1 || i != 1) {
                res += solve(pos + 1, newRestricted, i);
            }
        }
        return dp[pos][restricted][prev] = res;
    }
}