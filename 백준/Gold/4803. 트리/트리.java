
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static List<List<Integer>> tree;
  static boolean[] visited;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int round = 1;
    while (true) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      if (N == 0 && M == 0) break;

      tree = new ArrayList<>();
      visited = new boolean[N + 1];

      for (int i = 0; i <= N; i++) {
        tree.add(new ArrayList<>());
      }

      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        tree.get(from).add(to);
        tree.get(to).add(from);
      }
      pro(round++);
    }
  }

  static boolean check(int start) {
    int nodeCount = 1;
    int edgeCount = 0;
    Queue<Integer> q = new LinkedList<>();
    q.add(start);

    while (!q.isEmpty()) {
      int cur = q.poll();
      for (int next : tree.get(cur)) {
        if (visited[next]) {
          edgeCount++;
          continue;
        }
        visited[next] = true;
        nodeCount++;
        edgeCount++;
        q.add(next);
      }
    }
    return nodeCount == edgeCount / 2 + 1;
  }

  static void pro(int round) {
    StringBuilder sb = new StringBuilder();
    sb.append("Case " + round + ": ");

    int count = 0;

    for (int i = 1; i <= N; i++) {
      if (visited[i]) continue;
      visited[i] = true;
      if (check(i)) count++;
    }

    if (count == 1) sb.append("There is one tree.");
    else if (count > 1) sb.append("A forest of " + count + " trees.");
    else sb.append("No trees.");

    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}