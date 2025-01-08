
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int K, W, H;
  static int[][] board;
  static boolean[][][] visited;
  static int min = Integer.MAX_VALUE;
  static int[] dx = {1, 0, -1, 0, 2, 2, -2, -2, 1, 1, -1, -1};
  static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1, 2, -2, 2, -2};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    K = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    W = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    board = new int[H][W];
    for (int i = 0; i < H; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < W; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    visited = new boolean[H][W][K + 1];
  }

  static void bfs() {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{0, 0, 0, 0});
    visited[0][0][0] = true;
    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cx = info[0];
      int cy = info[1];
      int cd = info[2];
      int ck = info[3];

      if (cx == H - 1 && cy == W - 1) {
        min = Math.min(min, cd);
      }

      for (int i = 0; i < 12; i++) {
        if (ck == K && i > 3) {
          break;
        }
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        int nk = i > 3 ? ck + 1 : ck;
        int nd = cd + 1;

        if (nx < 0 || ny < 0 || nx > H - 1 || ny > W - 1) {
          continue;
        }

        if (board[nx][ny] == 1) {
          continue;
        }

        if (visited[nx][ny][nk]) {
          continue;
        }

        q.add(new int[]{nx, ny, nd, nk});
        visited[nx][ny][nk] = true;
      }
    }
  }

  static void pro() {
    bfs();
    System.out.println(min == Integer.MAX_VALUE ? -1 : min);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}