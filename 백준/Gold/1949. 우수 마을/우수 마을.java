
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1949 우수마을 : 골드2
 */
public class Main {
  static int N, R;
  static int[] peoples;
  static List<List<Integer>> tree = new ArrayList<>();
  static int[][] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    peoples = new int[N + 1];
    for (int i = 0; i <= N; i++) {
      tree.add(new ArrayList<>());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      peoples[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      tree.get(from).add(to);
      tree.get(to).add(from);
    }
    dp = new int[N + 1][2];
  }

  static void dfs(int x, int prev) {
    dp[x][0] = 0;
    dp[x][1] = peoples[x];
    
    for (int y : tree.get(x)) {
      if (y == prev) continue;
      dfs(y, x);
      dp[x][0] += Math.max(dp[y][0], dp[y][1]);
      dp[x][1] += dp[y][0];
    }
  }

  static void pro() {
    R = 1; // 임의로 루트노드는 1이라고 정해주기.
    dfs(R, -1);

    System.out.println(Math.max(dp[R][0], dp[R][1]));
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}