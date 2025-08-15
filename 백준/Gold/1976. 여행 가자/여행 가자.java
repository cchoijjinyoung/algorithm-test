
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static List<List<Integer>> graph = new ArrayList<>();
  static int[] route;
  static int start;
  static boolean[] visited;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    StringTokenizer st;
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        int connect = Integer.parseInt(st.nextToken());
        if (connect == 1) {
          graph.get(i).add(j);
        }
      }
    }
    route = new int[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      int city = Integer.parseInt(st.nextToken());
      route[i] = city;
    }
    start = route[0];
  }

  static void pro() {
    // start에서 bfs 해서 방문체크. route 배열에 방문체크가 false 인애가 존재하면 NO
    bfs();

    for (int city : route) {
      if (!visited[city]) {
        System.out.println("NO");
        return;
      }
    }
    System.out.println("YES");
  }

  static void bfs() {
    visited = new boolean[N + 1];
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    visited[start] = true;

    while (!q.isEmpty()) {
      int cur = q.poll();

      for (int next : graph.get(cur)) {
        if (visited[next]) {
          continue;
        }
        visited[next] = true;
        q.add(next);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}