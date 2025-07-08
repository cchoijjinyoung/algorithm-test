
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int MOD = 1000000003;
  static int[][] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());

    dp = new int[N + 1][N + 1];
  }

  static void pro() {
    for (int i = 1; i <= N; i++) {
      dp[i][1] = i;
      dp[i][0] = 1;
    }

    for (int i = 4; i <= N; i++) {
      for (int j = 1; j <= K; j++) {
        // i개 중 j개를 선택하는 방법 = 첫번째 색상을 선택한 경우(N - 2개 에서 K - 1개 선택) + 첫번째 색상을 선택하지 않은 경우(N - 1개 에서 K개 선택)
        dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
      }
    }
    System.out.println(dp[N][K]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}