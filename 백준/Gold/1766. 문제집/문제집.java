
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static List<List<Integer>> graph = new ArrayList<>();
  static int[] indeg;
  static PriorityQueue<Integer> pq = new PriorityQueue<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }
    indeg = new int[N + 1];

    M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());
      graph.get(A).add(B);
      indeg[B]++;
    }
  }

  static void pro() {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= N; i++) {
      if (indeg[i] == 0) {
        pq.add(i);
      }
    }

    while (!pq.isEmpty()) {
      int cur = pq.poll();
      sb.append(cur).append(' ');

      for (int next : graph.get(cur)) {
        indeg[next]--;
        if (indeg[next] == 0) {
          pq.add(next);
        }
      }
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}