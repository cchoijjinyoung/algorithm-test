
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static char[][] board;
  static int[][] visited;
  static int result;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new char[N][M];
    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        board[i][j] = line.charAt(j);
      }
    }
  }

  static void pro() {
    visited = new int[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (visited[i][j] == 1) {
          continue;
        }
        dfs(i, j);
      }
    }
    System.out.println(result);
  }

  static void dfs(int x, int y) {
    if (visited[x][y] == 2) {
      result++;
      return;
    } else if (visited[x][y] == 1) {
      return;
    }
    visited[x][y] = 2;
    if (board[x][y] == 'D') {
      dfs(x + 1, y);
    } else if (board[x][y] == 'L') {
      dfs(x, y - 1);
    } else if (board[x][y] == 'R') {
      dfs(x, y + 1);
    } else {
      dfs(x - 1, y);
    }
    visited[x][y] = 1;
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}