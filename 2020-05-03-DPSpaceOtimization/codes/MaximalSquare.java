package codes;

class MaximalSquare {
    public static void main(String[] args) {

    }

    public int maximalSquare1(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i + 1][j + 1] = 0;
                } else {
                    dp[i + 1][j + 1] = 1 + Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j]));
                    max = Math.max(max, dp[i + 1][j + 1]);
                }
            }
        }
        return max * max;
    }

    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[][] dp = new int[2][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[(i + 1) % 2][j + 1] = 0;
                } else {
                    dp[(i + 1) % 2][j + 1] = 1 + Math.min(dp[i % 2][j + 1], Math.min(dp[(i + 1) % 2][j], dp[i % 2][j]));
                    max = Math.max(max, dp[(i + 1) % 2][j + 1]);
                }
            }
        }
        return max * max;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[] dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int prev = 0;
            for (int j = 0; j < n; j++) {
                int temp = dp[j + 1];
                if (matrix[i][j] == '0') {
                    dp[j + 1] = 0;
                } else {
                    dp[j + 1] = 1 + Math.min(dp[j + 1], Math.min(dp[j], prev));
                    max = Math.max(max, dp[j + 1]);
                }
                prev = temp;
            }
        }
        return max * max;
    }
}