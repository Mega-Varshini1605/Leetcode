class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: single character
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Consider all substring lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // Print s[i] separately
                dp[i][j] = dp[i + 1][j] + 1;

                // Try merging with same characters
                for (int k = i + 1; k <= j; k++) {
                    if (s.charAt(i) == s.charAt(k)) {
                        int left = dp[i][k - 1];
                        int right = (k == j) ? 0 : dp[k + 1][j];

                        dp[i][j] = Math.min(dp[i][j], left + right);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}