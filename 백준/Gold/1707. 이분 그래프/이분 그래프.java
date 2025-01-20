
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int K;
  static int V, E;
  static List<List<Integer>> graph;
  static int[] group; // 1, -1
  static boolean[] visited;
  static String result;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    K = Integer.parseInt(st.nextToken());
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());

      graph = new ArrayList<>();
      for (int k = 0; k <= V; k++) {
        graph.add(new ArrayList<>());
      }
      group = new int[V + 1];
      visited = new boolean[V + 1];
      result = "YES";
      for (int j = 0; j < E; j++) {
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        graph.get(u).add(v);
        graph.get(v).add(u);
      }
      pro();
    }
  }

  static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    group[start] = 1;
    visited[start] = true;

    while (!q.isEmpty()) {
      int cur = q.poll();
      int color = group[cur];

      if (color == 1) {
        color = -1;
      } else {
        color = 1;
      }

      for (int next : graph.get(cur)) {
        if (group[next] != 0) {
          if (group[next] != color) {
            result = "NO";
            return;
          }
        }
        group[next] = color;
        if (visited[next]) continue;
        q.add(next);
        visited[next] = true;
      }
    }
  }

  static void pro() {
    for (int i = 1; i <= V; i++) {
      if (visited[i]) continue;
      bfs(i);
    }
    System.out.println(result);
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}