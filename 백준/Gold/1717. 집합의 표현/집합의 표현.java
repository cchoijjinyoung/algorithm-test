
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] parent;
  static int[][] query;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    parent = new int[N + 1];
    query = new int[M][3];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      query[i] = new int[]{type, a, b};
    }
  }

  static void pro() {
    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < query.length; i++) {
      int type = query[i][0];
      int a = query[i][1];
      int b = query[i][2];

      if (type == 0) {
        union(a, b);
      } else {
        print(a, b);
      }
    }
  }

  static void union(int a, int b) {
    if (a == b) return;

    int aRoot = find(a);
    int bRoot = find(b);

    parent[bRoot] = parent[aRoot];
  }

  static int find(int number) {
    if (number == parent[number]) return number;
    int root = find(parent[number]);
    parent[number] = root;
    return root;
  }

  static void print(int a, int b) {
    if (find(a) == find(b)) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}