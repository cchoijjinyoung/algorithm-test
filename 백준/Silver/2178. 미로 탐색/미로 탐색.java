
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로탐색, 실버1
 * 알고리즘 : 그래프 탐색
 */
class Main {
  static int N;
  static int M;
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    visited = new boolean[N][M];

    for (int i = 0; i < N; i++) {
      String s = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = s.charAt(j) - '0';
      }
    }
  }

  static void pro() {
    Queue<Integer> q = new LinkedList<>();
    q.add(0); // i
    q.add(0); // j
    q.add(1); // 거리
    visited[0][0] = true;

    while (!q.isEmpty()) {
      int x = q.poll();
      int y = q.poll();
      int d = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (map[nx][ny] == 0) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        if (nx == N - 1 && ny == M - 1) {
          System.out.println(d + 1);
          return;
        }

        q.add(nx);
        q.add(ny);
        q.add(d + 1);
        visited[nx][ny] = true;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}