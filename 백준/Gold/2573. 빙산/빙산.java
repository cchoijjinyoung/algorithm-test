import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] map;
  static int[][] temp;
  static boolean[][] visited;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  static void bfs(int x, int y) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{x, y});
    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cx = info[0];
      int cy = info[1];

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (temp[nx][ny] == 0) continue;
        if (visited[nx][ny]) continue;
        visited[nx][ny] = true;
        q.add(new int[]{nx, ny});
      }
    }
  }

  static void pro() {
    // 1보다 큰 지점에서 4방향 탐색(0이 있는 지) 새로운 배열에 업데이트
    int year = 0;
    while (true) {
      year++;
      temp = new int[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (map[i][j] == 0) continue;
          int zeroCount = 0;
          for (int d = 0; d < 4; d++) {
            int x = i + dx[d];
            int y = j + dy[d];
            if (map[x][y] == 0) {
              zeroCount++;
            }
          }
          temp[i][j] = Math.max(0, map[i][j] - zeroCount);
        }
      }
      // 만들어진 temp 배열 순회
      visited = new boolean[N][M];
      int count = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (temp[i][j] == 0) continue;
          if (visited[i][j]) continue;
          visited[i][j] = true;
          count++;
          if (count >= 2) {
            System.out.println(year);
            return;
          }
          bfs(i, j);
        }
      }
      if (count == 0) {
        System.out.println(0);
        return;
      }
      map = temp;
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}