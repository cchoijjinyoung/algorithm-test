
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
  static int N;
  static int root;
  static int removeNode;
  static List<List<Integer>> tree = new ArrayList<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    for (int i = 0; i < N; i++) {
      tree.add(new ArrayList<>());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int parent = Integer.parseInt(st.nextToken());
      if (parent == -1) {
        root = i;
        continue;
      }
      tree.get(parent).add(i);
    }

    removeNode = Integer.parseInt(br.readLine());
  }

  static int bfs() {
    if (removeNode == root) {
      return 0;
    }

    int count = 0;
    Queue<Integer> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
      int cur = q.poll();

      int childSize = tree.get(cur).size();

      if (childSize == 0) {
        count++;
      }

      if (childSize == 1 && tree.get(cur).get(0) == removeNode) {
        count++;
        continue;
      }

      for (int child : tree.get(cur)) {
        if (child == removeNode) {
          continue;
        }

        q.add(child);
      }
    }
    return count;
  }

  static void pro() {
    System.out.println(bfs());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}