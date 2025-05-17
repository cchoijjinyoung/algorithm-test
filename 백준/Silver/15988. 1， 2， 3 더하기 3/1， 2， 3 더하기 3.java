import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static long[] dp;
  static int T;
  static int MOD = 1_000_000_009;
  public static void main(String[] args) throws IOException {
    dp = new long[1000001];
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;

    for (int i = 4; i < 1000001; i++) {
      dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      int n = Integer.parseInt(br.readLine());
      System.out.println(dp[n]);
    }
  }
}
