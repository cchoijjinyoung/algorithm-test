
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 암호코드, 골드5
 * 알고리즘: dp(동적 프로그래밍)
 */
public class Main {
  static String password;
  static int N;
  static int[] dp;
  static int MOD = 1_000_000;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    password = br.readLine();
    N = password.length();
    dp = new int[N];
  }

  static boolean check(char pre, char post) {
    if (pre == '1') {
      return post <= '9';
    }

    if (pre == '2') {
      return post <= '6';
    }
    return false;
  }

  static void pro() {
    if (password.charAt(0) != '0') dp[0] = 1;

    for (int i = 1; i < N; i++) {
      // 단독으로 해석이 가능한 경우
      if (password.charAt(i) != '0') dp[i] = dp[i - 1];

      // i - 1번과 i번 숫자를 하나의 문자로 해석 가능할 때
      if (check(password.charAt(i - 1), password.charAt(i))) {
        if (i >= 2) dp[i] += dp[i - 2];
        else dp[i] += 1;
        dp[i] %= MOD;
      }
    }
    System.out.println(dp[N - 1]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}