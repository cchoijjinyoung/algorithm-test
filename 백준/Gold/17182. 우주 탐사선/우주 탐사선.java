
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int result = Integer.MAX_VALUE;
  static int[][] cost;
  static boolean[] visited;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    cost = new int[N][N];
    visited = new boolean[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        cost[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  static void pro() {
    // 플로이드 워셜
    for (int mid = 0; mid < N; mid++) {
      for (int S = 0; S < N; S++) {
        for (int E = 0; E < N; E++) {
          cost[S][E] = Math.min(cost[S][E], cost[S][mid] + cost[mid][E]);
        }
      }
    }
    // DFS
    visited[K] = true;
    dfs(K, 1, 0);
    System.out.println(result);
  }

  static void dfs(int cur, int cnt, int sum) {
    if (cnt == N) {
      result = Math.min(result, sum);
      return;
    }

    for (int next = 0; next < N; next++) {
      if (visited[next]) continue;
      visited[next] = true;
      dfs(next, cnt + 1, sum + cost[cur][next]);
      visited[next] = false;
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}