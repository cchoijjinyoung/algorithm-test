import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
  static int N;
  static List<List<Integer>> tree = new ArrayList<>();
  static boolean[] visited;
  static int[] result;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    visited = new boolean[N + 1];
    result = new int[N + 1];

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
  }

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();
    q.offer(1);
    visited[1] = true;

    while (!q.isEmpty()) {
      int cur = q.poll();

      for (int child : tree.get(cur)) {
        if (visited[child]) {
          continue;
        }
        visited[child] = true;
        result[child] = cur;
        q.offer(child);
      }
    }
  }

  static void pro() {
    bfs();

    for (int i = 2; i < result.length; i++) {
      System.out.println(result[i]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}