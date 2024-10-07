import java.util.*;
import java.io.*;

class Main {
    static int N; // 남은 일 수
    static int M; // 챕터의 수
    static int[][] board;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[M + 1][2];
        
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[M + 1][N + 1];
        
        for (int i = 1; i <= M; i++) {
            int w = board[i][0];
            int v = board[i][1];
            for (int j = 0; j <= N; j++) {
                // 책을 읽을 수 있으면,
                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[M][N]);
    }
}