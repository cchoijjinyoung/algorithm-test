
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int V, E;
  static int[] parent;
  static int total;
  static PriorityQueue<Node> pq = new PriorityQueue<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    parent = new int[V + 1];
    for (int i = 1; i <= V; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      pq.add(new Node(a, b, c));
    }
  }

  static int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }

  static boolean union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x != y) {
      parent[x] = y;
      return true;
    }
    return false;
  }

  static void pro() {
    while (!pq.isEmpty()) {
      Node node = pq.poll();
      int from = node.from;
      int to = node.to;

      if (union(from, to)) {
        total += node.w;
      }
    }
    System.out.println(total);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }

  static class Node implements Comparable<Node> {
    int to;
    int from;
    int w;

    public Node(int to, int from, int w) {
      this.to = to;
      this.from = from;
      this.w = w;
    }

    @Override
    public int compareTo(Node o) {
      return this.w - o.w;
    }
  }
}

