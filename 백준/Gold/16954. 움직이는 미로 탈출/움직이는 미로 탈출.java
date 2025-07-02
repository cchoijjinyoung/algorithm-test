
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static char[][] board;
  static int[] S, E;
  static int[] dx = {0, 0, 1, 1, 1, -1, -1, -1, 0};
  static int[] dy = {1, -1, 0, -1, 1, 0, -1, 1, 0};
  static boolean[][][] visited = new boolean[8][8][9];
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    board = new char[8][8];

    for (int i = 0; i < 8; i++) {
      String line = br.readLine();
      for (int j = 0; j < 8; j++) {
        char c = line.charAt(j);
        board[i][j] = c;
      }
    }
    S = new int[]{7, 0};
    E = new int[]{0, 7};
  }

  static void pro() {
    // bfs
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{S[0], S[1], 0});

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int cx = cur[0];
      int cy = cur[1];
      int second = cur[2];

      if (cx - second >= 0) {
        if (board[cx - second][cy] == '#') {
          continue;
        }
      }

      if (cx == E[0] && cy == E[1]) {
        System.out.println(1);
        return;
      }

      for (int i = 0; i < 9; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        int ns = Math.min(second + 1, 8);
        if (nx < 0 || ny < 0 || nx > 7 || ny > 7) {
          continue;
        }
        
        // 가고자 하는 곳에 벽이 있다면,
        if (nx - second >= 0) {
          if (board[nx - second][ny] == '#') {
            continue;
          }
        }

        if (visited[nx][ny][ns]) {
          continue;
        }
        visited[nx][ny][ns] = true;
        q.add(new int[]{nx, ny, ns});
      }
    }
    System.out.println(0);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}