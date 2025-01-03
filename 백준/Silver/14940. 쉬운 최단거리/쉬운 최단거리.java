
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 쉬운 최단거리 : 실버1
 */
public class Main {
  static int N, M;
  static int[][] board;
  static boolean[][] visited;
  static int[] target = new int[2];
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    visited = new boolean[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int number = Integer.parseInt(st.nextToken());
        board[i][j] = number;
        if (number == 2) {
          target[0] = i;
          target[1] = j;
          board[i][j] = 0;
        } else if (number == 1) {
          board[i][j] = -1;
        }
      }
    }
  }

  static void bfs() {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{target[0], target[1], 0});
    visited[target[0]][target[1]] = true;

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cx = info[0];
      int cy = info[1];
      int w = info[2];

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (board[nx][ny] == 0) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        board[nx][ny] = w + 1;
        q.add(new int[]{nx, ny, w + 1});
        visited[nx][ny] = true;
      }
    }
  }

  static void print() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  static void pro() {
    bfs();
    print();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}