
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    dp = new int[5006];
  }

  static void pro() {
    dp[3] = 1;
    dp[5] = 1;

    for (int i = 3; i <= 5000; i++) {
      if (dp[i] > 0) {
        if (dp[i + 3] == 0) {
          dp[i + 3] = dp[i] + 1;
        }

        if (dp[i + 5] == 0) {
          dp[i + 5] = dp[i] + 1;
        }
      }
    }
    System.out.println(dp[N] == 0 ? -1 : dp[N]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}