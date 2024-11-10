
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
  static List<List<int[]>> tree;
  static int[][] A, D;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    tree = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      tree.get(from).add(new int[]{to, w});
      tree.get(to).add(new int[]{from, w});
    }

    A = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      A[i] = new int[]{n1, n2};
    }

    D = new int[N + 1][N + 1];
  }

  static void dijkstra(int start) {
    PriorityQueue<int[]> q = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
    boolean[] visited = new boolean[N + 1];
    q.add(new int[]{start, 0});
    visited[start] = true;

    while (!q.isEmpty()) {
      int[] poll = q.poll();
      int cur = poll[0];
      int dist = poll[1];

      for (int[] nextInfo : tree.get(cur)) {
        int next = nextInfo[0];
        int weight = nextInfo[1];

        if (visited[next]) continue;
        q.add(new int[]{next, dist + weight});
        visited[next] = true;
        D[start][next] = dist + weight;
      }
    }
  }

  static void pro() {
    for (int i = 0; i < A.length; i++) {
      int start = A[i][0];
      dijkstra(start);
    }

    for (int i = 0; i < A.length; i++) {
      int from = A[i][0];
      int to = A[i][1];
      System.out.println(D[from][to]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}