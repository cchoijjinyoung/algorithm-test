
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 최소 비용 구하기, 골드5
 * 알고리즘 : 최단 거리 구하기
 */
public class Main {
  static int N, M, S, E, answer;
  static int[] dist;
  // static boolean[] visited;
  static ArrayList<Edge>[] graph;
  static PriorityQueue<int[]> pq;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    dist = new int[N + 1];
    // visited = new boolean[N + 1];

    graph = new ArrayList[N + 1];
    for (int i = 0; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }

    pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);

    StringTokenizer st;
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      Edge edge = new Edge();
      edge.idx = to;
      edge.weight = weight;
      graph[from].add(edge);
    }

    st = new StringTokenizer(br.readLine());
    S = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());
  }

  static void pro() {
    // dist 배열 초기화
    // S -> 각 정점으로 가는 비용의 최대값은 1억이므로 Integer로 충분
    // 마찬가지로 answer 또한 Integer 이다.
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[S] = 0;
    answer = 0;

    pq.add(new int[]{S, 0});

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int v = cur[0];
      int d = cur[1];

//      if (visited[v]) continue;
//      visited[v] = true;
      if (dist[v] < d) continue;

      for (Edge edge : graph[v]) {
        // 이미 더 작은 가중치로 가고 있으면, continue;
        if (dist[edge.idx] <= d + edge.weight) {
          continue;
        }

        dist[edge.idx] = d + edge.weight;

        pq.add(new int[]{edge.idx, dist[edge.idx]});
      }
    }
    System.out.println(dist[E]);
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