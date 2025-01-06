
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int[] coins, dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    coins = new int[N];
    dp = new int[K + 1];

    for (int i = 0; i < N; i++) {
      coins[i] = Integer.parseInt(br.readLine());
    }
  }

  static void pro() {
    dp[0] = 1;
    for (int i = 0; i < N; i++) {
      for (int j = coins[i]; j <= K; j++) {
        dp[j] += dp[j - coins[i]];
      }
    }
    System.out.println(dp[K]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}