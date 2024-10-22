
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] A;
  static int[][] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    A = new int[N + 1];
    dp = new int[N + 1][2];

    for (int i = 1; i <= N; i++) {
      A[i] = Integer.parseInt(br.readLine());
    }
  }

  static void pro() {
    dp[1][0] = A[1];
    dp[1][1] = A[1];

    if (N >= 2) {
      dp[2][0] = A[2];
      dp[2][1] = A[2] + A[1];

    }

    for (int i = 3; i <= N; i++) {
      dp[i][0] = Math.max(dp[i - 2][0] + A[i], dp[i - 2][1] + A[i]);
      dp[i][1] = dp[i - 1][0] + A[i];
    }
    System.out.println(Math.max(dp[N][0], dp[N][1]));
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}