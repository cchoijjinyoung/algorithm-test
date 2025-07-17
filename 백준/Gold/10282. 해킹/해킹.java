
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int T;
  static int n, d, c;
  static List<List<int[]>> graph;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      graph = new ArrayList<>();
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());
      c = Integer.parseInt(st.nextToken());

      for (int i = 0; i <= n; i++) {
        graph.add(new ArrayList<>());
      }

      for (int i = 0; i < d; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        graph.get(b).add(new int[]{a, s});
      }
      pro();
    }
  }

  static void pro() {
    Queue<int[]> q = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
    boolean[] visited = new boolean[n + 1];
    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    q.add(new int[]{c, 0});

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      if (visited[cur[0]]) continue;
      visited[cur[0]] = true;
      dist[cur[0]] = cur[1];

      for (int[] next : graph.get(cur[0])) {
        int to = next[0];
        if (visited[to]) continue;
        q.add(new int[]{to, cur[1] + next[1]});
      }
    }

    int count = 0;
    int duration = 0;
    for (int i = 1; i <= n; i++) {
      if (dist[i] == Integer.MAX_VALUE) continue;
      count++;
      duration = Math.max(duration, dist[i]);
    }

    System.out.println(count + " " + duration);
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}