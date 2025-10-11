
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] arr;
  static int[] parent;
  static int[][] query;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    query = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      query[i] = new int[]{a, b};
    }
    st = new StringTokenizer(br.readLine());
    arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    int answer = 0;
    parent = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < query.length; i++) {
      int a = find(query[i][0]);
      int b = find(query[i][1]);
      parent[b] = a;
    }

    int cur = find(arr[0]);
    for (int i = 1; i < arr.length; i++) {
      int next = find(arr[i]);
      if (cur != next) {
        cur = next;
        answer++;
      }
    }
    System.out.println(answer);
  }

  static int find(int a) {
    if (parent[a] == a) {
      return a;
    }
    return parent[a] = find(parent[a]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}