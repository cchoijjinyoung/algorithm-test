import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, E;
  static List<List<int[]>> graph;
  static int v1, v2;
  static int[] v1dist, v2dist;
  static int INF = Integer.MAX_VALUE;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      graph.get(from).add(new int[]{to, weight});
      graph.get(to).add(new int[]{from, weight});
    }
    st = new StringTokenizer(br.readLine());
    v1 = Integer.parseInt(st.nextToken());
    v2 = Integer.parseInt(st.nextToken());
    v1dist = new int[N + 1];
    v2dist = new int[N + 1];
  }

  static void dijkstra(int node) {
    int[] dist = new int[N + 1];
    Arrays.fill(dist, INF);
    boolean[] visited = new boolean[N + 1];
    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (n1, n2) -> n1[1] - n2[1]);
    pq.add(new int[]{node, 0});

    while (!pq.isEmpty()) {
      int[] info = pq.poll();
      int cur = info[0];
      int w = info[1];

      if (visited[cur]) {
        continue;
      }
      visited[cur] = true;
      dist[cur] = w;

      for (int[] next_info : graph.get(cur)) {
        int next = next_info[0];
        int next_w = next_info[1];
        if (visited[next]) {
          continue;
        }
        pq.add(new int[]{next, w + next_w});
      }
    }
    if (node == v1) {
      v1dist = dist;
    } else {
      v2dist = dist;
    }
  }

  static void pro() {
    dijkstra(v1);
    dijkstra(v2);

    if (v1dist[1] == INF || v1dist[v2] == INF || v1dist[N] == INF) {
      System.out.println(-1);
      return;
    }
    int root1 = v1dist[1] + v1dist[v2] + v2dist[N];
    int root2 = v2dist[1] + v1dist[v2] + v1dist[N];

    System.out.println(Math.min(root1, root2));
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}