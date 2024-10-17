
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
    for (int child : tree.get(node)) {
      if (visited[child]) {
        continue;
      }
      visited[child] = true;
      subTreeCounts[node] += dfs(child); // 자식 노드의 서브트리를 더해간다.
    }
    return subTreeCounts[node]; // 내 서브트리 수를 부모노드에게 리턴한다.
  }

  static void bfs() {
    visited[R] = true;

    for (int child : tree.get(R)) {
      visited[child] = true;
      subTreeCounts[R] += dfs(child);
    }
  }

  static void pro() {
    Arrays.fill(subTreeCounts, 1);
    bfs();

    for (int node : A) {
      System.out.println(subTreeCounts[node]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}