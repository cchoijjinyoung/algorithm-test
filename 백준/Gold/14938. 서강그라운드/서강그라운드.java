
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, M, R;
  static int[] item;
  static List<List<int[]>> graph;
  static int[][] dist;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    item = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      int t = Integer.parseInt(st.nextToken());
      item[i] = t;
    }

    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < R; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.get(from).add(new int[]{to, w});
      graph.get(to).add(new int[]{from, w});
    }
    dist = new int[N + 1][N + 1];
    for (int i = 0; i <= N; i++) {
      Arrays.fill(dist[i], Integer.MAX_VALUE);
    }
  }

  static void pro() {
    int result = 0;
    for (int start = 1; start < N; start++) {
      dijkstra(start);
    }

    for (int i = 1; i <= N; i++) {
      int itemCount = 0;
      for (int j = 1; j <= N; j++) {
        if (dist[i][j] <= M) {
          itemCount += item[j];
        }
      }
      result = Math.max(result, itemCount);
    }
    System.out.println(result);
  }

  static void dijkstra(int start) {
    boolean[] visited = new boolean[N + 1];
    PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
    pq.add(new int[]{start, 0});
    dist[start][start] = 0;

    while (!pq.isEmpty()) {
      int[] info = pq.poll();
      int cur = info[0];
      int w = info[1];
      visited[cur] = true;
      dist[start][cur] = Math.min(dist[start][cur], w);

      for (int[] nextInfo : graph.get(cur)) {
        int next = nextInfo[0];
        int nextW = nextInfo[1];

        if (visited[next]) continue;
        pq.add(new int[]{next, w + nextW});
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}