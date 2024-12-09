
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int R, C, answer;
  static char[][] board;
  static Set<Character> set = new HashSet<>();
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    board = new char[R][C];
    for (int i = 0; i < R; i++) {
      String line = br.readLine();
      for (int j = 0; j < C; j++) {
        board[i][j] = line.charAt(j);
      }
    }
  }

  static void dfs(int x, int y, int count) {
    if (x < 0 || y < 0 || x > R - 1 || y > C - 1) {
      answer = Math.max(answer, count);
      return;
    }

    if (set.contains(board[x][y])) {
      answer = Math.max(answer, count);
      return;
    }
    set.add(board[x][y]);
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];


      dfs(nx, ny, count + 1);
    }
    set.remove(board[x][y]);
  }

  static void pro() {
    dfs(0, 0, 0);
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}