import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] board;
  static int answer;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int[][] dp;
  static boolean[][] visited;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    visited = new boolean[N][M];
    dp = new int[N][M];
  }

  static void dfs(int x, int y) {
    if (visited[x][y]) {
      return;
    }

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
        continue;
      }

      if (board[x][y] >= board[nx][ny]) {
        continue;
      }

      dfs(nx, ny);
      dp[x][y] += dp[nx][ny];
    }
    visited[x][y] = true;
  }

  static void pro() {
    dp[0][0] = 1;
    visited[0][0] = true;
    dfs(N - 1, M - 1);
    System.out.println(dp[N - 1][M - 1]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}