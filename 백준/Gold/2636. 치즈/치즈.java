
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
  static int[][] board;
  static boolean[][] visited;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int[] melt = new int[101];
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  static int bfs(int t) {
    int meltCount = 0;
    List<int[]> cheese = new ArrayList<>();
    Queue<int[]> q = new LinkedList<>();
    visited = new boolean[N][M];
    q.add(new int[]{t, t});
    visited[t][t] = true;
    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cx = info[0];
      int cy = info[1];

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        visited[nx][ny] = true;
        if (board[nx][ny] == 1) {
          cheese.add(new int[]{nx, ny});
          continue;
        }
        q.add(new int[]{nx, ny});
      }
    }

    for (int[] point : cheese) {
      if (board[point[0]][point[1]] == 0) {
        continue;
      }
      meltCount++;
      board[point[0]][point[1]] = 0;
    }
    return meltCount;
  }

  static void pro() {
    int t = 0;
    int meltCount = 0;
    while (true) {
      meltCount = bfs(t);

      if (meltCount == 0) {
        System.out.println(t);
        System.out.println(melt[t]);
        break;
      }
      t++;
      melt[t] = meltCount;
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}