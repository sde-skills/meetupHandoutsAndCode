//Ref :https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
class Solution {
    String number;
    //pos, restricted, sum
    int[][][] dp = new int[21][2][21];
    Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));

    public int countPrimeSetBits(int L, int R) {
        number = Integer.toBinaryString(L - 1);
        resetDPArray();
        int low = solve(0, 1, 0);
        number = Integer.toBinaryString(R);
        resetDPArray();
        int high = solve(0, 1, 0);
        return high - low;
    }

    private int solve(int pos, int restricted, int sum) {
        if (pos == number.length()) {
            return primes.contains(sum) ? 1 : 0;
        }
        if (dp[pos][restricted][sum] != -1) {
            return dp[pos][restricted][sum];
        }

        int limit = (restricted > 0) ? number.charAt(pos) - '0' : 1;
        int res = 0;

        for (int i = 0; i <= limit; i++) {
            int newRestricted = restricted;
            if (i < limit) {
                newRestricted = 0;
            }
            //No need to check if the bit is '1' to pass the sum, as the only other case is '0' which
            //will not change the sum anyway
            res += solve(pos + 1, newRestricted, sum + i);
        }
        return dp[pos][restricted][sum] = res;
    }

    private void resetDPArray() {
        for (int[][] x : dp) {
            for (int[] y : x) {
                Arrays.fill(y, -1);
            }
        }
    }
}