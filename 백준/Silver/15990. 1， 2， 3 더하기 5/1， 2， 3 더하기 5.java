import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1, 2, 3 더하기 5
 */
public class Main {
  static int T;
  static int[] A;
  static int[][] dp;
  static int MOD = 1_000_000_009;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    A = new int[T];
    dp = new int[100001][4];

    for (int i = 0; i < T; i++) {
      A[i] = Integer.parseInt(br.readLine());
    }
  }

  static void pro() {
    dp[1][1] = 1; dp[1][2] = 0; dp[1][3] = 0;
    dp[2][1] = 0; dp[2][2] = 1; dp[2][3] = 0;
    dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

    for (int i = 4; i <= 100000; i++) {
      dp[i][1] = dp[i - 1][2] + dp[i - 1][3];
      dp[i][2] = dp[i - 2][1] + dp[i - 2][3];
      dp[i][3] = dp[i - 3][1] + dp[i - 3][2];
      dp[i][1] %= MOD;
      dp[i][2] %= MOD;
      dp[i][3] %= MOD;
    }

    for (int i = 0; i < A.length; i++) {
      int sum = dp[A[i]][1] + dp[A[i]][2];
      sum %= MOD;
      sum +=  dp[A[i]][3];
      System.out.println(sum % MOD);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}