
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] map;
  static PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
  static int island_idx = 0;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int answer = Integer.MAX_VALUE;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int value = Integer.parseInt(st.nextToken());
        if (value == 1) {
          map[i][j] = -1;
        }
      }
    }
  }

  static void pro() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (map[i][j] == -1) {
          marking(i, j);
        }
      }
    }

    for (int i = 1; i <= island_idx; i++) {
      search(i);
    }

    System.out.println(answer);
  }

  static void marking(int x, int y) {
    island_idx++;
    Queue<int[]> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][N];

    map[x][y] = island_idx;
    q.add(new int[]{x, y});
    pq.add(new int[]{x, y, 0});
    visited[x][y] = true;

    while (!q.isEmpty()) {
      int[] cur = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cur[0] + dx[i];
        int ny = cur[1] + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) {
          continue;
        }

        if (map[nx][ny] == 0) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        map[nx][ny] = island_idx;
        q.add(new int[]{nx, ny});
        pq.add(new int[]{nx, ny, 0});
        visited[nx][ny] = true;
      }
    }
  }

  static void search(int idx) {
    Queue<int[]> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (map[i][j] == idx) {
          q.add(new int[]{i, j, 0});
          visited[i][j] = true;
        }
      }
    }

    while (!q.isEmpty()) {
      int[] cur = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cur[0] + dx[i];
        int ny = cur[1] + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        if (map[nx][ny] != 0 && map[nx][ny] != island_idx) {
          answer = Math.min(answer, cur[2]);
          return;
        }

        q.add(new int[]{nx, ny, cur[2] + 1});
        visited[nx][ny] = true;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}