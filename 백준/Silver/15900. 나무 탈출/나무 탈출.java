
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

/**
 * 나무 탈출 : 실버1
 */
public class Main {
  static int N;
  static List<List<Integer>> tree;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    tree = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());

      tree.get(from).add(to);
      tree.get(to).add(from);
    }
  }

  static void pro() {
    int root = 1;
    int sum = 0;
    Set<Integer> visited = new HashSet<>();
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{root, 0});
    visited.add(root);

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int now = cur[0];
      int level = cur[1];

      if (now != root && tree.get(now).size() == 1) {
        sum += level;
        continue;
      }

      for (int next : tree.get(now)) {
        if (visited.contains(next)) {
          continue;
        }
        q.add(new int[]{next, level + 1});
        visited.add(next);
      }
    }

    if (sum % 2 == 0) {
      System.out.println("No");
    } else {
      System.out.println("Yes");
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}