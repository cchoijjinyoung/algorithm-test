
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 양, 실버1
 */
public class Main {
  static int R, C, sheep, wolf;
  static char[][] map;
  static boolean[][] visited;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    map = new char[R][C];
    visited = new boolean[R][C];

    for (int i = 0; i < R; i++) {
      String s = br.readLine();
      for (int j = 0; j < C; j++) {
        map[i][j] = s.charAt(j);
      }
    }
  }

  static void bfs(int x,  int y) {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0 ,-1};
    int o = 0;
    int v = 0;

    if (map[x][y] == 'o') o++;
    else if (map[x][y] == 'v') v++;

    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{x, y});
    visited[x][y] = true;

    while (!q.isEmpty()) {
      int[] cur = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cur[0] + dx[i];
        int ny = cur[1] + dy[i];

        if (nx < 0 || ny < 0 || nx > R - 1 || ny  > C - 1) continue;
        if (visited[nx][ny]) continue;
        if (map[nx][ny] == '#') continue;

        if (map[nx][ny] == 'o') o++;
        else if (map[nx][ny] == 'v') v++;

        q.add(new int[]{nx, ny});
        visited[nx][ny] = true;
      }
    }
    if (o > v) sheep += o;
    else wolf += v;
  }


  static void pro() {
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (map[i][j] == '#') continue;
        if (visited[i][j]) continue;
        bfs(i, j);
      }
    }
    System.out.println(sheep + " " + wolf);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}