
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static String S;
  static String[] words;
  static int[] dp;
  static int INF = 1_000_000_000;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    S = br.readLine();
    N = Integer.parseInt(br.readLine());
    words = new String[N];
    for (int i = 0; i < N; i++) {
      words[i] = br.readLine();
    }
  }

  static void pro() {
    int L = S.length();
    dp = new int[L + 1];
    Arrays.fill(dp, INF);
    dp[L] = 0;

    for (int i = L - 1; i >= 0; i--) {
      for (String word : words) {
        int len = word.length();

        if (i + len <= L) {
          if (dp[i + len] == INF) {
            continue;
          }
          String sub = S.substring(i, i + len);
          int cost = calcCost(word, sub);

          if (cost == INF) {
            continue;
          }
          dp[i] = Math.min(dp[i], cost + dp[i + len]);
        }
      }
    }
    System.out.println(dp[0] == INF ? -1 : dp[0]);
  }

  static int calcCost(String word, String sub) {
    char[] a = word.toCharArray();
    char[] b = sub.toCharArray();
    Arrays.sort(a);
    Arrays.sort(b);
    if (!Arrays.equals(a, b)) return INF;

    int diff = 0;
    for (int i = 0; i < word.length(); i++) {
      if (word.charAt(i) != sub.charAt(i)) {
        diff++;
      }
    }
    return diff;
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}