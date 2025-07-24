import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static List<List<int[]>> graph = new ArrayList<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      // 양방향
      graph.get(from).add(new int[]{to, w});
      graph.get(to).add(new int[]{from, w});
    }
  }

  static void pro() {
    prim();
  }

  static void prim() {
    PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
    boolean[] visited = new boolean[N + 1];
    pq.add(new int[]{1, 0});

    int result = 0;
    int max = Integer.MIN_VALUE;
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();

      if (visited[cur[0]]) continue;
      visited[cur[0]] = true;
      result += cur[1];
      max = Math.max(max, cur[1]);

      for (int[] next : graph.get(cur[0])) {
        if (visited[next[0]]) continue;
        pq.add(next);
      }
    }
    System.out.println(result - max);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}