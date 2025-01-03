
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int H, W;
  static int Sr, Sc, Fr, Fc;
  static int[][] board;
  static List<Point> list;
  static int[] dx = {0, 0, 1, -1};
  static int[] dy = {1, -1, 0, 0};
  static int answer = -1;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][M];
    list = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
        if (board[i][j] == 1) {
          board[i][j] = -1;
          list.add(new Point(i, j));
        }
      }
    }
    st = new StringTokenizer(br.readLine());
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    Sr = Integer.parseInt(st.nextToken()) - 1;
    Sc = Integer.parseInt(st.nextToken()) - 1;
    Fr = Integer.parseInt(st.nextToken()) - 1;
    Fc = Integer.parseInt(st.nextToken()) - 1;
  }

  static void bfs() {
    Queue<Point> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][M];
    q.add(new Point(Sr, Sc));
    visited[Sr][Sc] = true;

    while (!q.isEmpty()) {
      Point cur = q.poll();
      int cx = cur.x;
      int cy = cur.y;

      if (cx == Fr && cy == Fc) {
        answer = board[cx][cy];
        return;
      }

      for (int d = 0; d < 4; d++) {
        int nx = cx + dx[d];
        int ny = cy + dy[d];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }
        if (visited[nx][ny]) {
          continue;
        }
        if (!isPossible(nx, ny)) {
          continue;
        }
        if (board[nx][ny] == 0) {
          q.add(new Point(nx, ny));
          visited[nx][ny] = true;
          board[nx][ny] = board[cx][cy] + 1;
        }
      }
    }
  }

  static boolean isPossible(int x, int y) {
    if (x + H - 1 > N - 1 || y + W - 1 > M - 1) {
      return false;
    }

    for (int i = 0; i < list.size(); i++) {
      Point wall = list.get(i);
      int wx = wall.x;
      int wy = wall.y;

      if (wx >= x && wx <= x + H - 1 && wy >= y && wy <= y + W - 1) {
        return false;
      }
    }
    return true;
  }

  static void pro() {
    bfs();
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Point {
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}