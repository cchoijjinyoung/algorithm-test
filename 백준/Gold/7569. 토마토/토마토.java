import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M, H; // <= 100
  static int[][][] board;
  static boolean[][][] visited;
  static int[] dx = {0, 0, -1, 1, 0, 0}; // 위, 아래, 상, 하, 좌, 우
  static int[] dy = {0, 0, 0, 0, -1, 1};
  static int[] dz = {-1, 1, 0, 0, 0, 0};
  static int mellowCount, notMellowCount, emptyCount;
  static int result;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    board = new int[N][M][H];
    visited = new boolean[N][M][H];

    for (int k = 0; k < H; k++) {
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
          board[i][j][k] = Integer.parseInt(st.nextToken());
        }
      }
    }

    // 멀티 소스 BFS
    multiSourceBFS();
  }

  public static void multiSourceBFS() {
    Queue<int[]> q = new LinkedList<>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        for (int k = 0; k < H; k++) {
          if (board[i][j][k] == 1) {
            q.add(new int[]{i, j, k, 0});
            mellowCount++;
            visited[i][j][k] = true;
          } else if (board[i][j][k] == 0) {
            notMellowCount++;
          } else {
            emptyCount++;
          }
        }
      }
    }
    // 모두 익어있는 상태면, 0을 출력
    if (notMellowCount == 0) {
      System.out.println(0);
      return;
    }
    bfs(q);
  }

  public static void bfs(Queue<int[]> q) {
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int x = cur[0];
      int y = cur[1];
      int z = cur[2];
      int day = cur[3];
      result = Math.max(result, day);

      for (int i = 0; i < 6; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        int nz = z + dz[i];
        int nextDay = day + 1;

        if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || nz < 0 || nz > H - 1) continue;
        if (board[nx][ny][nz] != 0) continue;
        if (visited[nx][ny][nz]) continue;
        board[nx][ny][nz] = 1;
        mellowCount++;
        visited[nx][ny][nz] = true;
        q.add(new int[]{nx, ny, nz, nextDay});
      }
    }
    if (mellowCount != N * M * H - emptyCount) {
      System.out.println(-1);
    } else {
      System.out.println(result);
    }
  }
}
