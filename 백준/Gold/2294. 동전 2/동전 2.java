import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int[] coins;
  static int[] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    coins = new int[N];
    for (int i = 0; i < N; i++) {
      coins[i] = Integer.parseInt(br.readLine());
    }
    dp = new int[K + 1];
  }

  static void pro() {
    Arrays.sort(coins);
    Arrays.fill(dp, 10001);
    for (int coin_idx = coins.length - 1; coin_idx >= 0; coin_idx--) {
      int coin = coins[coin_idx];
      if (coin > K) continue;
      dp[coin] = 1;
      for (int target = coin + 1; target <= K; target++) {
          if (dp[target - coin] == 0) {
            continue;
          }
          
          dp[target] = Math.min(dp[target], dp[target - coin] + 1);
      }
    }
    System.out.println(dp[K] == 10001 ? -1 : dp[K]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}