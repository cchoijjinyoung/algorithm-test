
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
  static int[][] result;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      graph.add(new ArrayList<>());
    }
    result = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int p = Integer.parseInt(st.nextToken());
        if (p == 1) {
          graph.get(i).add(j);
        }
      }
    }
  }

  static void search(int start) {
    boolean[] visited = new boolean[N];
    Queue<Integer> q = new LinkedList<>();
    q.add(start);

    while(!q.isEmpty()) {
      int cur = q.poll();

      for (int next : graph.get(cur)) {
        if (visited[next]) continue;
        q.add(next);
        visited[next] = true;
      }
    }

    for (int i = 0; i < N; i++) {
      if (!visited[i]) continue;
      result[start][i] = 1;
    }
  }

  static void pro() {
    for (int i = 0; i < N; i++) {
      search(i);
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        sb.append(result[i][j]).append(' ');
      }
      sb.append('\n');
    }
    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}