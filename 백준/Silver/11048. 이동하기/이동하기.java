
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] map;
  static int[][] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    dp = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int candy = Integer.parseInt(st.nextToken());
        map[i][j] = candy;
      }
    }
  }

  static void pro() {
    dp[0][0] = map[0][0];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (i > 0) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + map[i][j]);
        }

        if (j > 0) {
          dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + map[i][j]);
        }

//        if (i > 0 && j > 0) {
//          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + map[i][j]);
//        }
      }
    }
    System.out.println(dp[N - 1][M - 1]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}