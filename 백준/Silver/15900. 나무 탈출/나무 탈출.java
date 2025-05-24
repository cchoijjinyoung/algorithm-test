import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static List<List<Integer>> graph;
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }
    visited = new boolean[N + 1];

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());

      graph.get(from).add(to);
      graph.get(to).add(from);
    }

    solve();
  }

  public static void solve() {
    int result = 0;
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{1, 0});
    visited[1] = true;

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cur = info[0];
      int depth = info[1];

      int count = 0;
      for (int next : graph.get(cur)) {
        if (visited[next]) continue;
        q.offer(new int[]{next, depth + 1});
        visited[next] = true;
        count++;
      }
      if (count == 0) {
        result += depth;
      }
    }
    if (result % 2 == 0) {
      System.out.println("No");
    } else {
      System.out.println("Yes");
    }
  }
}