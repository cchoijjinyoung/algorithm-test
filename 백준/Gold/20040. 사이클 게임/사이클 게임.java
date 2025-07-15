
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] parent;
  static int[][] edges;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    parent = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
    }

    M = Integer.parseInt(st.nextToken());
    edges = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      edges[i] = new int[]{from, to};
    }
  }

  static void pro() {
    for (int i = 0; i < M; i++) {
      int from = edges[i][0];
      int to = edges[i][1];

      if (find(from) == find(to)) {
        System.out.println(i + 1);
        return;
      } else {
        union(from, to);
      }
    }
    System.out.println(0);
  }

  static int find(int x) {
    if (x == parent[x]) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }

  static void union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x < y) {
      parent[y] = x;
    } else {
      parent[x] = y;
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}