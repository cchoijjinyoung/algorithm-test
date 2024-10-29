
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int T, MOD;
  static int[] N;
  static long[] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    N = new int[T];
    for (int i = 0; i < T; i++) {
      N[i] = Integer.parseInt(br.readLine());
    }

    dp = new long[1000001];
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
  }

  static void pro() {
    MOD = 1_000_000_009;

    for (int i = 4; i <= 1000000; i++) {
      long sum = dp[i - 3] + dp[i - 2] + dp[i - 1];
      dp[i] = sum % MOD;
    }

    for (int i = 0; i < N.length; i++) {
      System.out.println(dp[N[i]]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}