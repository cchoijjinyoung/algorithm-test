
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
  static List<List<Integer>> graph;

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

      graph.get(from).add(to);
      graph.get(to).add(from);
    }
  }

  static int[] dijkstra(int start) {
    int[] result = new int[N + 1];
    boolean[] visited = new boolean[N + 1];
    PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
    pq.add(new int[]{start, 0});
    visited[start] = true;

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();

      for (int next : graph.get(cur[0])) {
        if (visited[next]) continue;
        pq.add(new int[]{next, cur[1] + 1});
        result[next] = cur[1] + 1;
        visited[next] = true;
      }
    }
    return result;
  }

  static int search(int A, int B) {
    int result = 0;
    int[] distA = dijkstra(A);
    int[] distB = dijkstra(B);

    for (int i = 1; i <= N; i++) {
      result += Math.min(distA[i], distB[i]);
    }
    return result;
  }

  static void pro() {
    int A = 0, B = 0, min = Integer.MAX_VALUE;

    for (int i = 1; i <= N - 1; i++) {
      for (int j = i + 1; j <= N; j++) {
        int result = search(i, j);
        if (min > result) {
          min = result;
          A = i;
          B = j;
        }
      }
    }
    System.out.println(A + " " + B + " " + min * 2);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}