import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static final int[] dx = {1, 0 ,-1, 0};
  static final int[] dy = {0, 1, 0, -1};
  static int[][] map;
  static int n, m;
  static int max = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new int[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dfs(0);

    System.out.println(max);
  }

  static void dfs(int wallCount) {
    if (wallCount == 3) {
      bfs();
      return;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == 0) {
          map[i][j] = 1;
          dfs(wallCount + 1);
          map[i][j] = 0;
        }
      }
    }
  }

  static void bfs() {
    Queue<int[]> q = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == 2) {
          q.offer(new int[]{i, j});
        }
      }
    }

    int[][] copyMap = new int[n][m];

    for (int i = 0; i < n; i++) {
      copyMap[i] = map[i].clone();
    }

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int cx = cur[0];
      int cy = cur[1];
      
      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        
        if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1) {
          continue;
        }

        if (copyMap[nx][ny] == 0) {
          q.offer(new int[]{nx, ny});
          copyMap[nx][ny] = 2;
        }
      }
    }
    calc(copyMap);
  }
  
  static void calc(int[][] copyMap) {
    int safeZone = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (copyMap[i][j] == 0) {
          safeZone++;
        }
      }
    }

    max = Math.max(max, safeZone);
  }
}