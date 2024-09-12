import java.io.*;
import java.util.*;

class Main {
  static int N;
  static int M;
  static int[][] map;
  static int[] dx = {1, 0, -1, 0}; // 하, 우, 상, 좌
  static int[] dy = {0, 1, 0, -1};
  static int min = Integer.MAX_VALUE;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];

    for (int i = 0; i < N; i++) {
      String s = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = s.charAt(j) - '0';
      }
    }

    play();

    if (min == Integer.MAX_VALUE) {
      min = -1;
    }
    System.out.println(min);
  }

  static void play() {
    boolean[][][] visited = new boolean[N][M][2];

    Queue<int[]> q = new LinkedList<>();

    // x, y, move, 벽 break flag
    q.offer(new int[]{0, 0, 1, 0});
    visited[0][0][0] = true;

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int cx = cur[0];
      int cy = cur[1];
      int move = cur[2];
      int flag = cur[3];

      if (cx == N - 1 && cy == M - 1) {
        min = Math.min(min, move);
        break;
      }

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (visited[nx][ny][flag]) {
          continue;
        }

        if (map[nx][ny] == 0) {
          q.offer(new int[]{nx, ny, move + 1, flag});
          visited[nx][ny][flag] = true;
          continue;
        }

        // 벽을 만났을 때,
        // 이미 벽을 부셨었다면,
        if (flag == 1) {
          continue;
        }
        
        q.offer(new int[]{nx, ny, move + 1, 1});
        visited[nx][ny][1] = true;
      }
    }
  }
}