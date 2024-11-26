
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
  static List<List<Integer>> graph;
  static int[] indeg;
  static Queue<Integer> q = new LinkedList<>();

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }
    indeg = new int[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int number = Integer.parseInt(st.nextToken());
      int from = Integer.parseInt(st.nextToken());
      for (int j = 1; j < number; j++) {
        int to = Integer.parseInt(st.nextToken());
        graph.get(from).add(to);
        indeg[to]++;
        from = to;
      }
    }
  }

  static void pro() {
    StringBuilder sb = new StringBuilder();

    for (int singer = 1; singer <= N; singer++) {
      if (indeg[singer] == 0) {
        q.add(singer);
      }
    }

    while (!q.isEmpty()) {
      // indeg가 0인 애들만 q에 넣어야한다.
      int cur = q.poll();
      sb.append(cur).append('\n');
      N--;

      for (int next : graph.get(cur)) {
//        if (indeg[next] == 0) {
//          System.out.println(0);
//          return;
//        }
        if (indeg[next] > 0) {
          indeg[next]--;
          if (indeg[next] == 0) {
            q.add(next);
          }
        }
      }
    }
    if (N != 0) {
      System.out.println(0);
      return;
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}