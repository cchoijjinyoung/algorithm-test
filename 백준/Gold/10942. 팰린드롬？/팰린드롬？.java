
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] arr;
  static int[][] dp;
  static Query[] queries;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    arr = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    M = Integer.parseInt(br.readLine());
    queries = new Query[M];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      queries[i] = new Query(from, to);
    }

    dp = new int[N + 1][N + 1];
    for (int i = 0; i < N + 1; i++) {
      Arrays.fill(dp[i], -1);
    }
  }

  static int palindrome(int S, int E) {
    if (S >= E) {
      return 1;
    }

    if (dp[S][E] != -1) {
      return dp[S][E];
    }

    if (arr[S] == arr[E]) {
      return dp[S][E] = palindrome(S + 1, E - 1);
    }
    return 0;
  }

  static void pro() {
    StringBuilder sb = new StringBuilder();
    for (Query query : queries) {
      int S = query.start;
      int E = query.end;
      sb.append(palindrome(S, E)).append('\n');
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Query {
  int start;
  int end;

  Query(int start, int end) {
    this.start = start;
    this.end = end;
  }
}