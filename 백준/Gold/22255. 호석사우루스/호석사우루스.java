
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] S, E;
  static int[][] board;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    S = new int[2];
    E = new int[2];
    st = new StringTokenizer(br.readLine());
    S[0] = Integer.parseInt(st.nextToken()) - 1;
    S[1] = Integer.parseInt(st.nextToken()) - 1;
    E[0] = Integer.parseInt(st.nextToken()) - 1;
    E[1] = Integer.parseInt(st.nextToken()) - 1;
    board = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  static void pro() {
    bfs();
  }

  static void bfs() {
    boolean[][][] visited = new boolean[N][M][3];
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    Queue<int[]> q = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
    q.offer(new int[]{S[0], S[1], 0, 1});

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int moveCount = cur[3];

      if (cur[0] == E[0] && cur[1] == E[1]) {
        System.out.println(cur[2]);
        return;
      }

      int d = 0;
      int repeat = 4;

      if (moveCount % 3 == 1) {
        repeat = 2;
      } else if (moveCount % 3 == 2) {
        d = 2;
      }

      for (int i = d; i < repeat; i++) {
        int nx = cur[0] + dx[i];
        int ny = cur[1] + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (board[nx][ny] == -1) {
          continue;
        }

        if (visited[nx][ny][moveCount % 3]) continue;

        int nextDamage = cur[2] + board[nx][ny];
        q.offer(new int[]{nx, ny, nextDamage, moveCount + 1});
        visited[nx][ny][moveCount % 3] = true;
      }
    }
    System.out.println(-1);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}