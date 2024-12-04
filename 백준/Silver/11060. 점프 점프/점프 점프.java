
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] A;
  static int[] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    A = new int[N];
    dp = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 0; i < N; i++) {
      if (dp[i] == Integer.MAX_VALUE) {
        continue;
      }
      int move = A[i];
      for (int j = 1; j <= move; j++) {
        if (i + j < N) {
          dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
        }
      }
    }
    if (dp[N - 1] == Integer.MAX_VALUE) {
      System.out.println(-1);
      return;
    }
    System.out.println(dp[N - 1]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}