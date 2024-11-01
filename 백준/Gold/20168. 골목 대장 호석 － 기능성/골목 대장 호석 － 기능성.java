
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
  static int N, M, A, B, C, min;
  static List<List<int[]>> graph;
  static Set<Integer> visited;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    A = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    min = Integer.MAX_VALUE;

    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(from).add(new int[]{to, cost});
      graph.get(to).add(new int[]{from, cost});
    }

    visited = new HashSet<>();
  }

  static void dfs(int node, int totalCost, int maxCost) {
    if (node == B) {
      if (totalCost <= C) {
        min = Math.min(min, maxCost);
      }
      return;
    }

    for (int[] nextInfo : graph.get(node)) {
      int next = nextInfo[0];

      if (visited.contains(next)) continue;
      int cost = nextInfo[1];
      totalCost += cost;

      int temp = maxCost;
      maxCost = Math.max(maxCost, cost);
      visited.add(next);
      dfs(next, totalCost, maxCost);
      visited.remove(next);
      totalCost -= cost;
      maxCost = temp;
    }
  }

  static void pro() {
    dfs(A, 0, 0);
    if (min == Integer.MAX_VALUE) {
      System.out.println(-1);
    } else {
      System.out.println(min);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}