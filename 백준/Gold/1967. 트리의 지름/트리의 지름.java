
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static List<List<int[]>> graph;
  static int[] dist;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());

    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graph.get(from).add(new int[]{to, w});
      graph.get(to).add(new int[]{from, w});
    }
  }

  static void pro() {
    if (N == 1) {
      System.out.println(0);
      return;
    }
    int target = find(1);
    int to = find(target);
    System.out.println(dist[to]);
  }

  static int find(int node) {
    boolean[] visited = new boolean[N + 1];

    Queue<Integer> q = new LinkedList<>();
    q.add(node);
    dist = new int[N + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[node] = 0;
    visited[node] = true;

    while (!q.isEmpty()) {
      int cn = q.poll();

      for (int[] next : graph.get(cn)) {
        int nn = next[0];
        int nw = next[1];

        if (visited[nn]) {
          continue;
        }

        dist[nn] = dist[cn] + nw;
        visited[nn] = true;
        q.add(nn);
      }
    }

    int value = 0;
    int result = 0;
    for (int i = 1; i <= N; i++) {
      if (dist[i] > value) {
        value = dist[i];
        result = i;
      }
    }
    return result;
  }


  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}