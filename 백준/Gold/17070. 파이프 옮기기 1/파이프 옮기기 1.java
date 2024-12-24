
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] board;
  static int[][][] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    board = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dp = new int[N][N][3];
  }

  static void pro() {
    // dp 배열 초기화 - dp[0][N] 까지는 왼쪽에서 오는 경우의 수 1개뿐
    for (int i = 1; i < N; i++) {
      if (board[0][i] == 1) {
        break;
      }
      dp[0][i][0] = 1;
    }
    // board를 순회하면서 dp[조][건][0~2]까지 더한 값으로 업데이트
    // - board 순회는 (1, 2) 부터 시작해도 될듯
    for (int i = 1; i < N; i++) {
      for (int j = 2; j < N; j++) {
        if (board[i][j] == 1) {
          continue;
        }
        // 가로 모양으로 도달하는 방법
        dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

        // 세로 모양으로 도달하는 방법
        dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

        // 대각선 모양으로 도달하는 방법
        if (board[i][j - 1] == 1 || board[i - 1][j] == 1) {
          continue;
        }
        dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
      }
    }

    int answer = 0;
    for (int i = 0; i < 3; i++) {
      answer += dp[N - 1][N - 1][i];
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}