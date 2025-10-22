
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static long A, B;
  static long[] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    dp = new long[56];

    A = Long.parseLong(st.nextToken());
    B = Long.parseLong(st.nextToken());
  }

  static void pro() {
    dp[0] = 1;
    for (int i = 1; i <= 55; i++) {
      dp[i] = (long) (dp[i - 1] * 2 + Math.pow(2, i));
    }
    System.out.println(count(B) - count(A - 1));
  }

  static long count(long x) {
    if (x == 0) return 0;

    int k = 0;
    for (int i = 55; i >= 0; i--) {
      if ((x & (1L << i)) != 0) {
        k = i;
        break;
      }
    }

    long sum = k == 0 ? 0 : dp[k - 1];
    long pow = (long) Math.pow(2, k);
    long remainder = x - pow;

    return sum + remainder + 1 + count(remainder);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}