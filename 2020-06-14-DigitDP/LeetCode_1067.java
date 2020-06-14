//Ref : https://leetcode.com/problems/digit-count-in-range/
class Solution {
    //pos, restricted, sum, nonzero
    int[][][][] dp = new int[12][2][10][2];
    String num;

    public int digitsCount(int d, int low, int high) {
        resetDPArray();
        num = String.valueOf(low - 1);
        int L = solve(0, 1, 0, 0, d);
        resetDPArray();
        num = String.valueOf(high);
        int R = solve(0, 1, 0, 0, d);
        return R - L;
    }

    private int solve(int pos, int restricted, int sum, int nonzero, int d) {
        if (pos >= num.length()) {
            return nonzero > 0 ? sum : 0;
        }
        if (dp[pos][restricted][sum][nonzero] != -1) {
            return dp[pos][restricted][sum][nonzero];
        }

        int res = 0;
        int limit = (restricted > 0) ? num.charAt(pos) - '0' : 9;
        for (int i = 0; i <= limit; i++) {
            int newRestricted = restricted;
            if (i < limit) {
                newRestricted = 0;
            }
            int newNonZero = nonzero;
            if (i != 0) {
                newNonZero = 1;
            }
            if (newNonZero > 0 && i == d) {
                res += solve(pos + 1, newRestricted, sum + 1, newNonZero, d);
            } else {
                res += solve(pos + 1, newRestricted, sum, newNonZero, d);
            }
        }
        return dp[pos][restricted][sum][nonzero] = res;
    }

    private void resetDPArray() {
        for (int[][][] x : dp) {
            for (int[][] y : x) {
                for (int[] z : y) {
                    Arrays.fill(z, -1);
                }
            }
        }
    }
}