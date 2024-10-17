
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
  static int N, R, Q;
  static int[] A, subTreeCounts, result;
  static boolean[] visited;
  static List<List<Integer>> tree = new ArrayList<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    Q = Integer.parseInt(st.nextToken());

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

    A = new int[Q];
    for (int i = 0; i < Q; i++) {
      A[i] = Integer.parseInt(br.readLine());
    }

    visited = new boolean[N + 1];
    subTreeCounts = new int[N + 1];
    result = new int[Q];
  }

  static int dfs(int node) {
    subTreeCounts[node] = 1;

    for (int child : tree.get(node)) {
      if (visited[child]) {
        continue;
      }
      visited[child] = true;
      subTreeCounts[node] += dfs(child);
    }
    return subTreeCounts[node];
  }

  static void bfs() {
    visited[R] = true;

    int subTreeCount = 1;
    for (int child : tree.get(R)) {
      visited[child] = true;
      subTreeCount += dfs(child);
      subTreeCounts[R] = subTreeCount;
    }
  }

  static void pro() {
    bfs();

    for (int i = 0; i < A.length; i++) {
      System.out.println(subTreeCounts[A[i]]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}