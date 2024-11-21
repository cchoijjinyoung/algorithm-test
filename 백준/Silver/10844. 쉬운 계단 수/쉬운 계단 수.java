import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] dp;
  static int MOD = 1_000_000_000;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    dp = new int[N + 1][10];
  }

  static void pro() {
    for (int i = 1; i <= 9; i++) {
      dp[1][i] = 1;
    }

    for (int len = 2; len <= N; len++) {
      for (int prev = 0; prev <= 9; prev++) {
        int low = prev - 1;
        int high = prev + 1;
        if (low >= 0) {
          dp[len][low] += dp[len - 1][prev] % MOD;
        }
        if (high <= 9) {
          dp[len][high] += dp[len - 1][prev] % MOD;
        }
      }
    }

    // 출력
    int answer = 0;
    for (int i = 0; i <= 9; i++) {
      answer += dp[N][i] % MOD;
      answer %= MOD;
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}