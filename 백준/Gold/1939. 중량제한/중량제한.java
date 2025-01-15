
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static List<List<int[]>> graph;
  static int S, E;
  static boolean[] visited;
  static int answer = Integer.MIN_VALUE;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.get(from).add(new int[]{to, w});
      graph.get(to).add(new int[]{from, w});
    }
    st = new StringTokenizer(br.readLine());
    S = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    visited = new boolean[N + 1];
  }

  static void bfs() {
    Queue<int[]> q = new PriorityQueue<>((n1, n2) -> n2[1] - n1[1]);
    q.add(new int[]{S, Integer.MAX_VALUE});

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cur = info[0];
      int cw = info[1];
      visited[cur] = true;

      if (cur == E) {
        answer = cw;
        System.out.println(answer);
        return;
      }

      for (int[] nextInfo : graph.get(cur)) {
        int next = nextInfo[0];
        int nw = nextInfo[1];

        if (visited[next]) {
          continue;
        }

        nw = Math.min(nw, cw);
        q.add(new int[]{next, nw});
      }
    }
  }

  static void pro() {
    bfs();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}