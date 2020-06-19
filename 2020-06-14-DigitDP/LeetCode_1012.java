//Ref : https://leetcode.com/problems/numbers-with-repeated-digits/
class Solution {
    String num;
    //pos, restricted, nonzero, mask
    //zero is handled separately. Suppose if we are making a number 5, by following the path say : '0005'
    //If, we update masks for 0, two times (since 00 is prefix), then the mask will say that 0 has been chosen
    //two times and will not let the next number chosen. We make that check while dealing with masks.
    int[][][][] dp = new int[11][2][2][1 << 11];

    public int numDupDigitsAtMostN(int N) {
        return N - countNumbersWithUniqueDigits(N);
    }

    public int countNumbersWithUniqueDigits(int n) {
        num = String.valueOf(n);
        resetDPArray();
        return solve(0, 1, 0, 0);
    }

    private int solve(int pos, int restricted, int nonzero, int mask) {
        if (pos == num.length()) {
            return nonzero > 0 ? 1 : 0;
        }

        if (dp[pos][restricted][nonzero][mask] != -1) {
            return dp[pos][restricted][nonzero][mask];
        }

        int limit = restricted > 0 ? num.charAt(pos) - '0' : 9;
        int res = 0;

        for (int i = 0; i <= limit; i++) {
            int newRestricted = restricted;
            if (i < limit) {
                newRestricted = 0;
            }
            int newNonZero = nonzero;
            if (i != 0) {
                newNonZero = 1;
            }
            //Make sure not to update masks for numbers who haven't yet started or not being started now
            //Think 001 vs 100 (in 2nd case it is ok to update the mask for 0, but not in the first case)
            int newMask = mask;
            if (newNonZero > 0)
                newMask |= (1 << i);

            //Check if current bit is already included in the mask or not
            int currentBit = (mask & (1 << i));
            if (currentBit == 0) {
                res += solve(pos + 1, newRestricted, newNonZero, newMask);
            }
        }
        return dp[pos][restricted][nonzero][mask] = res;
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