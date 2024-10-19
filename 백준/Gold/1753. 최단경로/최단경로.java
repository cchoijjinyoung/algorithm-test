
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최단경로, 골드4
 * 알고리즘 : 최단 거리 구하기
 * 가중치 10 이하의 자연수 : 다익스트라
 */
public class Main {
  static int V, E, K;
  static int[] dist;
  static boolean[] visited;
  static PriorityQueue<int[]> pq;
  static ArrayList<Edge>[] graph;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    V = Integer.parseInt(st.nextToken());
    dist = new int[V + 1];
    visited = new boolean[V + 1];
    pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);

    E = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(br.readLine());

    graph = new ArrayList[V + 1];
    for (int i = 0; i <= V; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      Edge edge = new Edge();
      edge.idx = v;
      edge.weight = w;
      graph[u].add(edge);
    }
  }

  static void pro() {
    // dist 배열은 Integer 여도 되는가? 20000(v의 갯수) * 10(w) = 200000 이므로 가능
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[K] = 0;

    pq.add(new int[]{K, 0});

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int v = cur[0];
      int d = cur[1];

      if (visited[v]) continue;
      visited[v] = true;

      for (Edge edge : graph[v]) {
        if (dist[edge.idx] < d + edge.weight) {
          continue;
        }

        dist[edge.idx] = d + edge.weight;
        pq.add(new int[]{edge.idx, dist[edge.idx]});
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= V; i++) {
      if (dist[i] == Integer.MAX_VALUE) {
        sb.append("INF").append('\n');
        continue;
      }
      sb.append(dist[i]).append('\n');
    }
    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }

  static class Edge {
    int idx;
    int weight;
  }
}