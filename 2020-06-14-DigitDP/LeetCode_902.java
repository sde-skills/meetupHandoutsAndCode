//Ref : https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
class Solution {
    int[][][] dp = new int[10][2][2];
    String str;

    public int atMostNGivenDigitSet(String[] D, int N) {
        int res = 0;
        str = String.valueOf(N);
        for (int[][] x : dp) {
            for (int[] y : x) {
                Arrays.fill(y, -1);
            }
        }
        res = solve(0, 0, 1, D);
        return res;
    }

    private int solve(int pos, int start, int restricted, String[] validDigits) {
        if (pos == str.length()) {
            if (start > 0) {
                return 1;
            }
            return 0;
        }
        if (dp[pos][start][restricted] != -1) {
            return dp[pos][start][restricted];
        }

        int res = 0;
        int limit = restricted > 0 ? str.charAt(pos) - '0' : Integer.valueOf(validDigits[validDigits.length - 1]);

        //if we haven't started creating numbers yet, we can defer and un-restrict next digits selection
        if (start == 0) {
            res = solve(pos + 1, 0, 0, validDigits);
        }

        //whether we have started creating numbers or not, we can always start (or continue building up) new numbers
        for (int i = 0; i < validDigits.length; i++) {
            int curr = Integer.valueOf(validDigits[i]);
            if (curr > limit)
                break;
            int newRestricted = restricted;
            if (curr < limit) {
                newRestricted = 0;
            }
            res += solve(pos + 1, 1, newRestricted, validDigits);
        }

        return dp[pos][start][restricted] = res;
    }
}