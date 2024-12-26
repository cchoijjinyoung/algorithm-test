
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, M, X;
  static List<List<int[]>> graph;
  static int[][] dist;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int time = Integer.parseInt(st.nextToken());
      graph.get(from).add(new int[]{to, time});
    }

    dist = new int[N + 1][N + 1];
  }

  static void dijkstra(int node) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);
    boolean[] visited = new boolean[N + 1];
    pq.add(new int[]{node, 0});
    while (!pq.isEmpty()) {
      int[] info = pq.poll();
      int cur = info[0];
      int curTime = info[1];
      if (visited[cur]) {
        continue;
      }
      visited[cur] = true;
      dist[node][cur] = curTime;

      for (int[] nextInfo : graph.get(cur)) {
        int next = nextInfo[0];
        int addTime = nextInfo[1];
        pq.add(new int[]{next, curTime + addTime});
      }
    }
  }

  static void pro() {
    for (int i = 1; i <= N; i++) {
      dijkstra(i);
    }
    int max = 0;
    for (int i = 1; i <= N; i++) {
      max = Math.max(max, dist[X][i] + dist[i][X]);
    }

    System.out.println(max);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}