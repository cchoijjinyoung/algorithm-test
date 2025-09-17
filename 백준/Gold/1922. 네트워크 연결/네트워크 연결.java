
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
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    StringTokenizer st;
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(a).add(new int[]{b, cost});
      graph.get(b).add(new int[]{a, cost});
    }
  }

  static void pro() {
    prim();
  }

  static void prim() {
    int count = 0;
    int answer = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
    boolean[] visited = new boolean[N + 1];
    pq.add(new int[]{1, 0});

    while(!pq.isEmpty()) {
      int[] cur = pq.poll();
      int node = cur[0];
      int cost = cur[1];

      if (visited[node]) {
        continue;
      }
      visited[node] = true;

      answer += cost;
      count++;

      if (count == N) {
        System.out.println(answer);
        return;
      }

      for (int[] next : graph.get(node)) {
        int next_node = next[0];
        int next_cost = next[1];

        if (visited[next_node]) continue;
        pq.add(new int[]{next_node, next_cost});
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}