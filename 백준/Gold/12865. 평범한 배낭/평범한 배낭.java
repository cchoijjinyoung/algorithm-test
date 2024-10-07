import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int K;
    static int[][] board;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N + 1][2];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[N + 1][K + 1];
        
        // N과 K를 순회한다. 물건과 무게
        // n번째 물건을 m번째 무게에 담을 수 있는 지 확인
        for (int i = 1; i <= N; i++) {
            int w = board[i][0];
            int v = board[i][1];
            for (int j = 0; j <= K; j++) {
                if (w <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}