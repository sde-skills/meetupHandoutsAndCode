package codes;
class UniquePaths {
    public static void main(String[] args) {

    }

    public int uniquePaths1(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j<n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[2][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i % 2][j] = 1;
                } else {
                    dp[i % 2][j] = dp[(i - 1) % 2][j] + dp[i % 2][j - 1];
                }
            }
        }
        return dp[(m - 1) % 2][n - 1];
    }

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j] + dp[j-1];
                }
            }
        }
        return dp[n - 1];
    }
}