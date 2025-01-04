
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static List<List<Integer>> tree;
  static int[] depths, parents;
  static int[][] queries;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    depths = new int[N + 1];
    parents = new int[N + 1];
    tree = new ArrayList<>();

    for (int i = 0; i <= N; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      tree.get(from).add(to);
      tree.get(to).add(from);
    }

    M = Integer.parseInt(br.readLine());
    queries = new int[M][2];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      queries[i] = new int[]{a, b};
    }
  }

  static void dfs(int cur, int depth, int parent) {
    depths[cur] = depth;
    parents[cur] = parent;
    for (int next : tree.get(cur)) {
      if (next == parent) {
        continue;
      }
      dfs(next, depth + 1, cur);
    }
  }

  static void LCA(int n1, int n2) {
    int depth1 = depths[n1];
    int depth2 = depths[n2];

    while (depth1 != depth2) {
      if (depth1 > depth2) {
        n1 = parents[n1];
        depth1--;
      } else {
        n2 = parents[n2];
        depth2--;
      }
    }

    while (n1 != n2) {
      n1 = parents[n1];
      n2 = parents[n2];
    }

    System.out.println(n1);
  }

  static void pro() {
    dfs(1, 1, 0);
    for (int[] query : queries) {
      int n1 = query[0];
      int n2 = query[1];
      LCA(n1, n2);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}