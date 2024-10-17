
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 회사문화1, 골드4
 * 알고리즘 : 트리
 */
class Main {
  static int N;
  static int M;
  static int[][] A;
  static int[] points;
  static List<List<Integer>> graph = new ArrayList<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    A = new int[M][2];
    points = new int[N + 1];

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      int boss = Integer.parseInt(st.nextToken());
      if (boss == -1) {
        continue;
      }
      graph.get(boss).add(i);
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int employee = Integer.parseInt(st.nextToken());
      int point = Integer.parseInt(st.nextToken());
      points[employee] += point;
    }
  }

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();
    q.add(1); // owner

    while (!q.isEmpty()) {
      int boss = q.poll();

      int point = points[boss];

      for (int employee : graph.get(boss)) {
        points[employee] += point;
        q.add(employee);
      }
    }
  }

  static void pro() {
    bfs();
    for (int i = 1; i < points.length; i++) {
      System.out.print(points[i] + " ");
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}