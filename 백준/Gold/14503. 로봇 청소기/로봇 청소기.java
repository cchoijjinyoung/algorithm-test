
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int r, c, d;
  static int[][] board;
  static int[] dx = {-1, 0, 1, 0}; // 북동남서
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  static void bfs() {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{r, c, d});
    board[r][c] = 2;

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cx = info[0];
      int cy = info[1];
      int cd = info[2];

      for (int i = 1; i <= 5; i++) {
        int nd = (cd - i + 4) % 4;
        int nx = cx;
        int ny = cy;

        if (i == 5) {
          nx -= dx[cd];
          ny -= dy[cd];

          if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1 || board[nx][ny] == 1) {
            return;
          }

          q.add(new int[]{nx, ny, cd});
          break;
        }

        nx += dx[nd];
        ny += dy[nd];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (board[nx][ny] == 1 || board[nx][ny] == 2) {
          continue;
        }

        q.add(new int[]{nx, ny, nd});
        board[nx][ny] = 2;
        break;
      }
    }
  }

  static void pro() {
    bfs();

    int answer = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] == 2) {
          answer++;
        }
      }
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}