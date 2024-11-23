
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, result;
  static int[] A;
  static long[][] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    A = new int[N - 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N - 1; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
    result = Integer.parseInt(st.nextToken());

    dp = new long[N - 1][21];
  }

  static void pro() {
    dp[0][A[0]] = 1;

    for (int i = 1; i < A.length; i++) {
      for (int j = 0; j <= 20; j++) {
        int minus = j - A[i];
        int plus = j + A[i];

        if (minus >= 0) {
          dp[i][minus] += dp[i - 1][j];
        }

        if (plus <= 20) {
          dp[i][plus] += dp[i - 1][j];
        }
      }
    }
    System.out.println(dp[N - 2][result]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}