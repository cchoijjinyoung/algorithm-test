import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[2][target + 1];
        
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= 20; j++) {
                
                // 불을 쏠 경우
                if (i >= 50) {
                    if (dp[0][i] > dp[0][i - 50] + 1) {
                        dp[0][i] = dp[0][i - 50] + 1;
                        dp[1][i] = dp[1][i - 50] + 1;
                    } else if (dp[0][i] == dp[0][i - 50] + 1) {
                        dp[1][i] = Math.max(dp[1][i], dp[1][i - 50] + 1);
                    }
                }
                
                // 싱글을 쏠 경우
                if (i >= j) {
                    if (dp[0][i] > dp[0][i - j] + 1) {
                        dp[0][i] = dp[0][i - j] + 1;
                        dp[1][i] = dp[1][i - j] + 1;
                    } else if (dp[0][i] == dp[0][i - j] + 1) {
                        dp[1][i] = Math.max(dp[1][i], dp[1][i - j] + 1);
                    }
                }
                
                // 더블을 쏠 경우
                if (i >= j * 2) {
                    if (dp[0][i] > dp[0][i - 2 * j] + 1) {
                        dp[0][i] = dp[0][i - 2 * j] + 1;
                        dp[1][i] = dp[1][i - 2 * j];
                    }
                }
                
                // 트리플을 쏠 경우
                if (i >= j * 3) {
                    if (dp[0][i] > dp[0][i - 3 * j] + 1) {
                        dp[0][i] = dp[0][i - 3 * j] + 1;
                        dp[1][i] = dp[1][i - 3 * j];
                    }
                }
            }
        }
        return new int[]{dp[0][target], dp[1][target]};
    }
}