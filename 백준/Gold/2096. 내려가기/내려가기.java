
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] board;
  static int min, max;
  static int[][][] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    board = new int[N][3];
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  static int max() {
    for (int i = 1; i < N; i++) {
      dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][1][0]) + board[i][0];
      dp[i][1][0] = Math.max(dp[i - 1][0][0], Math.max(dp[i - 1][1][0], dp[i - 1][2][0])) + board[i][1];
      dp[i][2][0] = Math.max(dp[i - 1][1][0], dp[i - 1][2][0]) + board[i][2];
    }
    return Math.max(dp[N - 1][0][0], Math.max(dp[N - 1][1][0], dp[N - 1][2][0]));
  }

  static int min() {
    for (int i = 1; i < N; i++) {
      dp[i][0][1] = Math.min(dp[i - 1][0][1], dp[i - 1][1][1]) + board[i][0];
      dp[i][1][1] = Math.min(dp[i - 1][0][1], Math.min(dp[i - 1][1][1], dp[i - 1][2][1])) + board[i][1];
      dp[i][2][1] = Math.min(dp[i - 1][1][1], dp[i - 1][2][1]) + board[i][2];
    }
    return Math.min(dp[N - 1][0][1], Math.min(dp[N - 1][1][1], dp[N - 1][2][1]));
  }

  static void pro() {
    dp = new int[N][3][2];
    dp[0][0][0] = board[0][0];
    dp[0][1][0] = board[0][1];
    dp[0][2][0] = board[0][2];

    StringBuilder sb = new StringBuilder();
    sb.append(max()).append(' ');

    dp[0][0][1] = board[0][0];
    dp[0][1][1] = board[0][1];
    dp[0][2][1] = board[0][2];
    sb.append(min());
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}