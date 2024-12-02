
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static char[][] board;
  static boolean[][] visited;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int[] answer = new int[2];
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    board = new char[N][N];

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < N; j++) {
        board[i][j] = line.charAt(j);
      }
    }
  }

  static void bfs(int x, int y, char color, int type) {
    if (type == 0) answer[0]++;
    else answer[1]++;

    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{x, y});
    visited[x][y] = true;

    while (!q.isEmpty()) {
      int[] point = q.poll();
      int cx = point[0];
      int cy = point[1];

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) {
          continue;
        }

        if (visited[nx][ny]) continue;

        char nextColor = board[nx][ny];
        if (type == 0) {
          if (nextColor != color) {
            continue;
          }
        } else {
          if (color == 'B' && nextColor != color) {
            continue;
          } else if ((color == 'R' || color == 'G') && nextColor == 'B') {
            continue;
          }
          color = nextColor;
        }
        q.add(new int[]{nx, ny});
        visited[nx][ny] = true;
      }
    }
  }


  static void pro() {
    visited = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (visited[i][j]) continue;
        bfs(i, j, board[i][j], 0);
      }
    }

    visited = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (visited[i][j]) continue;
        bfs(i, j, board[i][j], 1);
      }
    }
    System.out.println(answer[0] + " " + answer[1]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}