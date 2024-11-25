class Solution {
    int N;
    int[][] dp1, dp2;
    public int solution(int sticker[]) {
        N = sticker.length;
        
        if (N == 1) return sticker[0];
        
        // 첫번째 스티커 선택한 dp -> 마지막 스티커는 선택 못함 -> n - 2까지만 돌린다.
        dp1 = new int[N][2];
        dp1[0][1] = sticker[0];
        for (int i = 1; i <= N - 2; i++) {
            dp1[i][1] = dp1[i - 1][0] + sticker[i];
            dp1[i][0] = Math.max(dp1[i - 1][1], dp1[i - 1][0]);
        }
        
        // 첫번째 스티커 선택 X -> 마지막 스티커까지 돌린다.
        dp2 = new int[N][2];
        dp2[1][1] = sticker[1];
        for (int i = 2; i <= N - 1; i++) {
            dp2[i][1] = dp2[i - 1][0] + sticker[i];
            dp2[i][0] = Math.max(dp2[i - 1][1], dp2[i - 1][0]);
        }

        return Math.max(Math.max(dp1[N - 2][0], dp1[N - 2][1]), Math.max(dp2[N - 1][0], dp2[N - 1][1]));
    }
}