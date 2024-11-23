import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int MOD = 1_000_000_000;
  static int[][][][] dp; // [len][last][min][max]

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    dp = new int[N + 1][10][10][10];
  }

  static void pro() {
    int answer = 0;

    for (int i = 1; i <= 9; i++) {
      dp[1][i][i][i] = 1;
    }

    for (int len = 2; len <= N; len++) {
      for (int last = 0; last <= 9; last++) {
        for (int min = 0; min <= 9; min++) {
          for (int max = 0; max <= 9; max++) {
            int up = last + 1;
            int down = last - 1;

            if (down >= 0) {
              int update_min = Math.min(min, down);
              int update_max = Math.max(max, down);
              dp[len][down][update_min][update_max] += dp[len - 1][last][min][max];
              dp[len][down][update_min][update_max] %= MOD;
            }

            if (up <= 9) {
              int update_min = Math.min(min, up);
              int update_max = Math.max(max, up);
              dp[len][up][update_min][update_max] += dp[len - 1][last][min][max];
              dp[len][up][update_min][update_max] %= MOD;
            }
          }
        }
      }
    }

    for (int last = 0; last <= 9; last++) {
      answer += dp[N][last][0][9];
      answer %= MOD;
    }

    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}