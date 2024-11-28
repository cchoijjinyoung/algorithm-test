
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
  static int T, N;
  static int node1, node2;
  static int[] parents;
  static List<List<Integer>> tree;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      N = Integer.parseInt(br.readLine());
      parents = new int[N + 1];
      initializeTree();

      for (int j = 0; j < N - 1; j++) {
        st = new StringTokenizer(br.readLine());
        int parent = Integer.parseInt(st.nextToken());
        int child = Integer.parseInt(st.nextToken());
        parents[child] = parent;
        // tree.get(child).add(parent);
      }

      st = new StringTokenizer(br.readLine());
      node1 = Integer.parseInt(st.nextToken());
      node2 = Integer.parseInt(st.nextToken());
      pro();
    }
  }

  static void initializeTree() {
    tree = new ArrayList<>();
    for (int node = 0; node <= N; node++) {
      tree.add(new ArrayList<>());
    }
  }

  static void pro() {
    Set<Integer> set = new HashSet<>();
    Queue<Integer> q = new LinkedList<>();
    q.add(node1);
    q.add(node2);
    set.add(node1);
    set.add(node2);

    while (!q.isEmpty()) {
      int cur = q.poll();

      // 현재 노드가 루트 노드면 continue;
      if (parents[cur] == 0) {
        continue;
      }

      int parent = parents[cur];
      if (set.contains(parent)) {
        System.out.println(parent);
        return;
      }
      set.add(parent);
      q.add(parent);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}