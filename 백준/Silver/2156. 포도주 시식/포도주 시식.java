
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int N;
  static int[] wines;
  static int[][] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    wines = new int[N];
    dp = new int[N][2];

    for (int i = 0; i < N; i++) {
      wines[i] = Integer.parseInt(br.readLine());
    }
  }

  static void pro() {
    // dp[cur][select]
    if (N == 1) {
      System.out.println(wines[0]);
      return;
    }

    dp[0][1] = wines[0];
    dp[1][0] = wines[0];
    dp[1][1] = wines[0] + wines[1];

    for (int cur = 2; cur < N; cur++) {
      dp[cur][1] = Math.max(dp[cur - 2][0] + wines[cur - 1], dp[cur - 1][0]) + wines[cur]; // X O + cur, O X + cur
      dp[cur][0] = Math.max(dp[cur - 1][1], dp[cur - 1][0]); // O, X
    }

    System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}