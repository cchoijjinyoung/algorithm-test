import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 기타리스트, 실버1
 * 알고리즘 : dp
 */
public class Main {
  static int N, S, M;
  static int[] V;
  static boolean[][] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 곡의 갯수
    S = Integer.parseInt(st.nextToken()); // Start 볼륨
    M = Integer.parseInt(st.nextToken()); // Max 볼륨

    V = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      V[i] = Integer.parseInt(st.nextToken());
    }

    dp = new boolean[N + 1][M + 1];
  }

  static void pro() {
    dp[0][S] = true;

    int answer = 0;
    for (int i = 1; i <= N; i++) {
      boolean flag = false;
      answer = 0;
      for (int prev = 0; prev <= M; prev++) {
        if (!dp[i - 1][prev]) continue;

        for (int cur : new int[]{prev - V[i], prev + V[i]}) {
          if (cur < 0 || cur > M) continue;
          answer = Math.max(answer, cur);
          dp[i][cur] = true;
          flag = true;
        }
      }
      if (!flag) {
        System.out.println(-1);
        return;
      }
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}