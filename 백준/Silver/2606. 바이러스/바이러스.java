
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
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      graph.get(from).add(to);
      graph.get(to).add(from);
    }
  }

  static int bfs() {
    int result = 0;
    boolean[] visited = new boolean[N + 1];
    Queue<Integer> q = new LinkedList<>();
    q.add(1);
    visited[1] = true;

    while (!q.isEmpty()) {
      int node = q.poll();

      for (int child : graph.get(node)) {
        if (visited[child]) {
          continue;
        }

        q.add(child);
        visited[child] = true;
        result++;
      }
    }
    return result;
  }

  static void pro() {
    int answer = bfs();
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}