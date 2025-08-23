
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int M;
  static int[] ward;
  static List<List<int[]>> graph = new ArrayList<>();
  static boolean[] visited;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    ward = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      ward[i] = Integer.parseInt(st.nextToken());
    }
    ward[N - 1] = 2;

    for (int i = 0; i < N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      graph.get(a).add(new int[]{b, t});
      graph.get(b).add(new int[]{a, t});
    }
    visited = new boolean[N];
  }

  static void pro() {
    dijkstra();
  }

  static void dijkstra() {
    PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> Long.compare(e1.e, e2.e));
    pq.add(new Node(0, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      if (visited[cur.v]) continue;
      visited[cur.v] = true;
      if (cur.v == N - 1) {
        System.out.println(cur.e);
        return;
      }

      for (int[] next : graph.get(cur.v)) {
        if (ward[next[0]] == 1) continue;
        if (visited[next[0]]) continue;
        pq.add(new Node(next[0], cur.e + next[1]));
      }
    }
    System.out.println(-1);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }

  static class Node {
    int v;
    long e;

    Node(int v, long e) {
      this.v = v;
      this.e = e;
    }
  }
}