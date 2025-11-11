
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int R, C;
  static Queue<int[]> fireQ = new LinkedList<>();
  static Queue<int[]> jihunQ = new LinkedList<>();
  static char[][] board;
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
        char c = line.charAt(j);
        board[i][j] = c;
        if (c == 'J') {
          jihunQ.add(new int[]{i, j, 0});
        } else if (c == 'F') {
          fireQ.add(new int[]{i, j, 0});
        }
      }
    }
  }

  static void pro() {
    int t = 0;
    while (!jihunQ.isEmpty()) {
      // 불
      while (!fireQ.isEmpty()) {
        int[] cur = fireQ.peek();
        if (cur[2] != t) {
          break;
        }

        cur = fireQ.poll();

        for (int i = 0; i < 4; i++) {
          int nx = cur[0] + dx[i];
          int ny = cur[1] + dy[i];
          int nt = cur[2] + 1;

          if (nx < 0 || ny < 0 || nx > R - 1 || ny > C - 1) {
            continue;
          }

          if (board[nx][ny] == 'F' || board[nx][ny] == '#') {
            continue;
          }

          board[nx][ny] = 'F';
          fireQ.add(new int[]{nx, ny, nt});
        }
      }

      // 지훈
      while (!jihunQ.isEmpty()) {
        int[] cur = jihunQ.peek();

        if (cur[2] != t) {
          break;
        }

        cur = jihunQ.poll();

        if (cur[0] == 0 || cur[1] == 0 || cur[0] == R - 1 || cur[1] == C - 1) {
          System.out.println(cur[2] + 1);
          return;
        }

        for (int i = 0; i < 4; i++) {
          int nx = cur[0] + dx[i];
          int ny = cur[1] + dy[i];
          int nt = cur[2] + 1;

          if (nx < 0 || ny < 0 || nx > R - 1 || ny > C - 1) {
            continue;
          }

          if (board[nx][ny] == 'F' || board[nx][ny] == '#' || board[nx][ny] == 'J') {
            continue;
          }

          if (nx == 0 || ny == 0 || nx == R - 1 || ny == C - 1) {
            System.out.println(nt + 1);
            return;
          }

          board[nx][ny] = 'J';
          jihunQ.add(new int[]{nx, ny, nt});
        }
      }

      t++;
    }
    System.out.println("IMPOSSIBLE");
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}