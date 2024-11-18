class Solution {
    int[][] dp;
    int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[m][n];
        
        for (int[] puddle : puddles) {
            int x = puddle[0] - 1;
            int y = puddle[1] - 1;
            dp[x][y] = -1;
        }
        
        if (dp[0][1] != -1) dp[0][1] = 1;
        if (dp[1][0] != -1) dp[1][0] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0 && j == 1) continue;
                if (i == 1 && j == 0) continue;
                if (dp[i][j] == -1) continue;
                
                if (i != 0 && dp[i - 1][j] != -1) {
                    dp[i][j] += dp[i - 1][j];
                    dp[i][j] %= MOD;
                }
                
                if (j != 0 && dp[i][j - 1] != -1) {
                    dp[i][j] += dp[i][j - 1];
                    dp[i][j] %= MOD;
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}