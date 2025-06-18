
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] root;
  static int[][] query;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    root = new int[N + 1];
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
      root[i] = i;
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
    // arr[a]의 값, arr[b]의 값을 도출
    // arr[a]가 a랑 다르면 계속 반복
    int aRoot = find(a);
    int bRoot = find(b);

    root[bRoot] = root[aRoot];
  }

  static int find(int number) {
    while (root[number] != number) {
      number = root[number];
    }
    return number;
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