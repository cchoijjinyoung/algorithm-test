
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static List<Integer> list = new ArrayList<>();
  static int[][][] dp;
  static int INF = 1000000000;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    while (true) {
      int num = Integer.parseInt(st.nextToken());
      if (num == 0) {
        break;
      }
      list.add(num);
    }
    N = list.size();
    dp = new int[N + 1][5][5];
  }

  static void pro() {
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j < 5; j++) {
        Arrays.fill(dp[i][j], INF);
      }
    }

    dp[0][0][0] = 0;

    for (int i = 0; i < N; i++) {
      int next = list.get(i);
      for (int l = 0; l < 5; l++) {
        for (int r = 0; r < 5; r++) {
          int cur = dp[i][l][r];
          if (cur == INF) {
            continue;
          }

          if (next != r) {
            int cost = cur + moveCost(l, next);
            dp[i + 1][next][r] = Math.min(dp[i + 1][next][r], cost);
          }

          if (next != l) {
            int cost = cur + moveCost(r, next);
            dp[i + 1][l][next] = Math.min(dp[i + 1][l][next], cost);
          }
        }
      }
    }

    int answer = INF;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        answer = Math.min(answer, dp[N][i][j]);
      }
    }
    System.out.println(answer);
  }

  static int moveCost(int from, int to) {
    if (from == to) return 1;
    if (from == 0) return 2;
    if (Math.abs(from - to) == 2) return 4;
    return 3;
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}