import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static long[] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    dp = new long[N + 1];
  }

  static void pro() {
    if (N <= 6) {
      System.out.println(N);
      return;
    }
      
    for (int i = 1; i <= 6; i++) {
      dp[i] = i;
    }

    for (int i = 7; i <= N; i++) {
      for (int j = 3; j <= 5; j++) {
        dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
      }
    }
    System.out.println(dp[N]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}