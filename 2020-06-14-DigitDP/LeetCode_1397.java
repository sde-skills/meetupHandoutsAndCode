//Ref : https://leetcode.com/problems/find-all-good-strings/
class Solution {
    int MOD = (int) 1e9 + 7;
    int dp[][][] = new int[510][51][2];
    int[] lps;

    private int[] createLPS(String str) {
        int n = str.length();
        int[] lps = new int[n];
        lps[0] = 0;
        int k = 0;
        for (int i = 1; i < n; i++) {
            while (k > 0 && str.charAt(i) != str.charAt(k)) {
                k = lps[k - 1];
            }
            if (str.charAt(i) == str.charAt(k)) {
                k++;
            }
            lps[i] = k;
        }
        return lps;
    }

    // KMP
    private int getNextMatch(String str, int match, char ch) {
        int newMatch = match;
        while (newMatch > 0 && ch != str.charAt(newMatch)) {
            newMatch = lps[newMatch - 1];
        }
        if (ch == str.charAt(newMatch)) {
            newMatch++;
        }
        return newMatch;
    }

    private void resetDPArray() {
        for (int[][] x : dp) {
            for (int[] y : x) {
                Arrays.fill(y, -1);
            }
        }
    }

    private int solve(String str, String evil, int pos, int matchSoFar, int restricted) {
        if (matchSoFar == evil.length()) {
            return 0;
        }
        if (pos == str.length()) {
            return 1;
        }
        if (dp[pos][matchSoFar][restricted] != -1) {
            return dp[pos][matchSoFar][restricted];
        }
        char limit = restricted == 1 ? str.charAt(pos) : 'z';
        int res = 0;
        for (char ch = 'a'; ch <= limit; ch++) {
            int newRestricted = (restricted == 1 && ch == limit) ? 1 : 0;
            int newMatchSoFar = getNextMatch(evil, matchSoFar, ch);
            res = (res + solve(str, evil, pos + 1, newMatchSoFar, newRestricted)) % MOD;
        }
        return dp[pos][matchSoFar][restricted] = res;
    }

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        lps = createLPS(evil);
        resetDPArray();
        int L = solve(s1, evil, 0, 0, 1);
        resetDPArray();
        int R = solve(s2, evil, 0, 0, 1);
        int res = R - L;
        if (res < 0) {
            res += MOD;
        }
        if (!s1.contains(evil)) {
            res = (res + 1) % MOD;
        }
        return res;
    }
}