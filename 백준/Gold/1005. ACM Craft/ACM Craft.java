import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
  static int T, N, K, W;
  static int[] D;
  static int[] indeg;
  static List<List<Integer>> graph;

  static void input(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    st = new StringTokenizer(br.readLine());
    D = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      D[i] = Integer.parseInt(st.nextToken());
    }

    indeg = new int[N + 1];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());

      graph.get(from).add(to);
      indeg[to]++;
    }

    W = Integer.parseInt(br.readLine());
  }

  static void pro() {
    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (b1, b2) -> b1[1] - b2[1]);

    for (int i = 1; i <= N; i++) {
      // 가장 먼저 지을 수 있는 건물
      if (indeg[i] == 0) {
        pq.add(new int[]{i, D[i]});
      }
    }

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();

      if (cur[0] == W) {
        System.out.println(cur[1]);
        return;
      }
      for (int to : graph.get(cur[0])) {
        indeg[to]--;
        if (indeg[to] == 0) {
          pq.add(new int[]{to, cur[1] + D[to]});
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      input(br);
      pro();
    }
  }
}