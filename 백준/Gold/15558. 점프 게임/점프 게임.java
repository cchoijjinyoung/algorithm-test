
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int[][] board;
  static boolean[][] visited;
  static int[] dx;
  static int[] dy;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    board = new int[2][N];
    String line = br.readLine();
    for (int i = 0; i < line.length(); i++) {
      board[0][i] = line.charAt(i) - '0';
    }

    line = br.readLine();
    for (int i = 0; i < line.length(); i++) {
      board[1][i] = line.charAt(i) - '0';
    }

    visited = new boolean[2][N];
    dx = new int[]{0, 0, 1};
    dy = new int[]{1, -1, K};
  }

  static void bfs() {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{0, 0, 0}); // 왼, idx, 사라진칸 idx
    visited[0][0] = true;

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cx = info[0];
      int cy = info[1];
      int wall = info[2];

      for (int i = 0; i < 3; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (i == 2 && cx == 1) {
          nx -= 2;
        }

        if (ny > N - 1) {
          System.out.println(1);
          return;
        }

        if (ny < wall + 1 || board[nx][ny] == 0 || visited[nx][ny]) {
          continue;
        }
        visited[nx][ny] = true;
        q.add(new int[]{nx, ny, wall + 1});
      }
    }
    System.out.println(0);
  }

  static void pro() {
    bfs();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}