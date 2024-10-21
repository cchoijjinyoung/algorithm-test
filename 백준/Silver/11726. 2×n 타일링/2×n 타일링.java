import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    dp = new int[N + 1];
  }

  static void pro() {
    if (N == 1) {
      System.out.println(1);
      return;
    }

    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= N; i++) {
      dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
    }
    System.out.println(dp[N] % 10007);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}