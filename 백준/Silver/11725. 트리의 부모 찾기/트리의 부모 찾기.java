
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static List<List<Integer>> tree;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    tree = new ArrayList<>();

    for (int i = 0; i <= N; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 1; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());

      tree.get(from).add(to);
      tree.get(to).add(from);
    }
  }

  static void bfs() {
    int root = 1;
    int[] result = new int[N + 1];
    boolean[] visited = new boolean[N + 1];
    Queue<Integer> q = new LinkedList<>();
    q.add(root);
    visited[root] = true;
    while (!q.isEmpty()) {
      int cur = q.poll();

      for (int next : tree.get(cur)) {
        if (visited[next]) continue;
        result[next] = cur;
        q.add(next);
        visited[next] = true;
      }
    }
    for (int i = 2; i < result.length; i++) {
      System.out.println(result[i]);
    }
  }

  static void pro() {
    bfs();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}