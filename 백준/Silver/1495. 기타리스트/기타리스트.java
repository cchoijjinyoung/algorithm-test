import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, S, M;
  static int[] V;
  static boolean[][] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    V = new int[N + 1];
    dp = new boolean[N + 1][M + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      V[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    dp[0][S] = true;

    for (int i = 1; i <= N; i++) {
      for (int j = 0; j <= M; j++) {
        if (dp[i - 1][j]) {
          int minus = j - V[i];
          int plus = j + V[i];

          if (minus >= 0) dp[i][minus] = true;
          if (plus <= M) dp[i][plus] = true;
        }
      }
    }

    for (int i = M; i >= 0; i--) {
      if (dp[N][i]) {
        System.out.println(i);
        return;
      }
    }
    System.out.println(-1);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}