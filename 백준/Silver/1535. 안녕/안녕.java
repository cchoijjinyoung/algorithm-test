import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int K = 99;
    static int[][] board;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        board = new int[N + 1][2];
        dp = new int[N + 1][K + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            board[i][0] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            board[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // dp : n번째 사람까지 인사했을때의 행복도
        // n번째 사람을 m의 체력에 
        for (int n = 1; n <= N; n++) {
            int h = board[n][1]; // happy -> n한테 인사할 때의 행복도
            int s = board[n][0]; // sad
            for (int m = 0; m <= K; m++) {
                // 현재 체력에 인사할 수 있니
                if (m >= s) {
                    int gap = dp[n - 1][m - s];
                    dp[n][m] = Math.max(dp[n - 1][m], gap + h);
                } else {
                    dp[n][m] = dp[n - 1][m];
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}