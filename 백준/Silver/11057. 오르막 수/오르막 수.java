import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 오르막 수, 실버1
 * 알고리즘 : dp
 */
public class Main {
  static int N;
  static int[] sum;
  static int[][] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    dp = new int[N + 1][10];
    sum = new int[N + 1];
  }

  static void pro() {
    for (int i = 0; i <= 9; i++) {
      dp[1][i] = 1;
    }

    for (int i = 1; i <= N; i++) {
      dp[i][0] = 1; // i 길이의 숫자의 맨 뒤에가 0일 때, 오름차수들의 갯수
      sum[i] += 1;
    }
    sum[1] = 10; // 1자리 수의 오름차수 갯 수

    for (int i = 2; i <= N; i++) {
      for (int j = 1; j <= 9; j++) {
        dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 10007;
        sum[i] += dp[i][j];
        sum[i] %= 10007;
      }
    }
    System.out.println(sum[N]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}