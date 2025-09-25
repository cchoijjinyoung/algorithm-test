import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int INF = Integer.MAX_VALUE / 2;
  static int N;
  static int[][] W;
  static int[][] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    W = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        W[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    dp = new int[1 << N][N];
    for (int i = 0; i < (1 << N); i++) {
      Arrays.fill(dp[i], -1);
    }
  }

  static void pro() {
    int answer = tsp(1 << 0, 0);
    System.out.println(answer);
  }

  static int tsp(int mask, int cur) {
    if (mask == (1 << N) - 1) { // 모든 도시를 방문했으면,
      if (W[cur][0] == 0) {
        return INF;
      } else {
        return W[cur][0];
      }
    }

    // 이미 방문한 도시
    if (dp[mask][cur] != -1) {
      return dp[mask][cur];
    }

    int result = INF;
    for (int next = 0; next < N; next++) {
      // next가 visited 면,
      if ((mask & (1 << next)) != 0) continue;
      // next가 이동 불가면,
      if (W[cur][next] == 0) continue;

      int nextMask = mask | (1 << next); // next 방문 체크
      int cost = W[cur][next] + tsp(nextMask, next);
      if (cost < result) {
        result = cost;
      }
    }
    dp[mask][cur] = result;
    return result;
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}