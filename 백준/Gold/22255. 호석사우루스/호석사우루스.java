
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 호석사우루스 : 골드2
 */
public class Main {
  static int N, M, Sx, Sy, Ex, Ey;
  static int min = Integer.MAX_VALUE;
  static int[][] board;
  static boolean[][][] visited;

  static int[] dx = new int[]{-1, 1, 0, 0}; // for (i = 0 ~ 4) // 상하좌우
  static int[] dy = new int[]{0, 0, -1, 1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    Sx = Integer.parseInt(st.nextToken()) - 1;
    Sy = Integer.parseInt(st.nextToken()) - 1;
    Ex = Integer.parseInt(st.nextToken()) - 1;
    Ey = Integer.parseInt(st.nextToken()) - 1;

    board = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    visited = new boolean[N][M][3];
  }

  static void bfs() {
    PriorityQueue<int[]> q = new PriorityQueue<>((n1, n2) -> n1[3] - n2[3]);
    q.add(new int[]{Sx, Sy, 1, 0});
    visited[Sx][Sy][1] = true;

    while (!q.isEmpty()) {
      int[] poll = q.poll();
      int x = poll[0];
      int y = poll[1];
      int type = poll[2];
      int damage = poll[3];

      // type = 0
      int idx = 0;
      int repeat = 4;
      if (type == 1) { // 상, 하
        repeat = 2;
      } else if (type == 2) { // 좌, 우
        idx = 2;
      }

      for (int i = idx; i < repeat; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx == Ex && ny == Ey) {
          min = Math.min(min, damage);
          continue;
        }

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (board[nx][ny] == -1) {
          continue;
        }

        if (visited[nx][ny][(type + 1) % 3]) {
          continue;
        }

        q.add(new int[]{nx, ny, (type + 1) % 3, damage + board[nx][ny]});
        visited[nx][ny][(type + 1) % 3] = true;
      }
    }
    System.out.println(min == Integer.MAX_VALUE ? -1 : min);
  }

  static void pro() {
    bfs();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}