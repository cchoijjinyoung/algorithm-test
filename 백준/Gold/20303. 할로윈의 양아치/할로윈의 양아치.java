
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N, M, K;
  static int[] c;
  static int[] parent;
  static int[][] group;
  static List<Item> list = new ArrayList<>();
  static long[][] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    c = new int[N + 1];
    parent = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      c[i] = Integer.parseInt(st.nextToken());
      parent[i] = i;
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      union(a, b);
    }
  }

  static void union(int a, int b) {
    a = find(a);
    b = find(b);

    if (a != b) {
      parent[a] = b;
    }
  }

  static int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }

  static void pro() {
    // 그룹의 인원 수, 그룹의 사탕 수
    group = new int[N + 1][2];
    for (int i = 1; i <= N; i++) {
      int groupNumber = find(i);
      group[groupNumber][0]++;
      group[groupNumber][1] += c[i];
    }

    for (int i = 1; i <= N; i++) {
      if (parent[i] == i) {
        list.add(new Item(group[i][0], group[i][1]));
      }
    }
    list.sort((o1, o2) -> o1.w - o2.w);

    // dp[i][j] = 아이템 i개 까지 고려했을 때, 가방 크키 j로 얻을 수 있는 최대가치
    // 문제에서의 최대가치는 K -1 이다
    dp = new long[list.size() + 1][K];
    for (int i = 1; i <= list.size(); i++) {
      int curW = list.get(i - 1).w;
      int curV = list.get(i - 1).v;
      for (int j = 0; j < K; j++) {
        // 현재 아이템의 무게가 j보다 무겁다면 담을 수 없다
        if (curW > j) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j - curW] + curV, dp[i - 1][j]);
        }
      }
    }
    System.out.println(dp[list.size()][K - 1]);
  }

  static class Item {
    int w;
    int v;

    Item(int w, int v) {
      this.w = w;
      this.v = v;
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}