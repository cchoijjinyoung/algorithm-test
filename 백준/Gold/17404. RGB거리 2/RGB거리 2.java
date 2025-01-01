
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] board;
  static int[][][] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    board = new int[N + 1][3];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      board[i][0] = r;
      board[i][1] = g;
      board[i][2] = b;
    }
    dp = new int[N + 1][3][3];
  }

  static void pro() {
    int INF = 1000 * 1000;
    dp[1][0][0] = board[1][0];
    dp[1][0][1] = INF;
    dp[1][0][2] = INF;

    dp[1][1][1] = board[1][1];
    dp[1][1][0] = INF;
    dp[1][1][2] = INF;

    dp[1][2][2] = board[1][2];
    dp[1][2][0] = INF;
    dp[1][2][1] = INF;

    for (int start_color = 0; start_color < 3; start_color++) {
      for (int cur_home = 2; cur_home <= N; cur_home++) {
        for (int cur_color = 0; cur_color < 3; cur_color++) {
          dp[cur_home][start_color][cur_color] = board[cur_home][cur_color] +
              Math.min(dp[cur_home - 1][start_color][(cur_color + 1) % 3],
                  dp[cur_home - 1][start_color][(cur_color + 2) % 3]);
        }
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (i == j) {
          continue;
        }
        min = Math.min(min, dp[N][i][j]);
      }
    }
    System.out.println(min);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}