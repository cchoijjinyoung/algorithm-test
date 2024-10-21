
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1, 2, 3 더하기, 실버3
 * 알고리즘: DP
 */
public class Main {
  static int T;
  static int[] N, dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    N = new int[T];
    for (int i = 0; i < T; i++) {
      N[i] = Integer.parseInt(br.readLine());
    }
  }

  static void pro() {
    dp = new int[12];
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;

    for (int i = 4; i <= 11; i++) {
      dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
    }

    for (int i = 0; i < T; i++) {
      System.out.println(dp[N[i]]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}