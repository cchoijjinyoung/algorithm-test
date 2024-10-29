
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 토마토, 골드5
 */
public class Main {
  static int N, M, H, answer;
  static int[][][] tomato;
  static int[] dx = {1, 0, -1, 0, 0, 0};
  static int[] dy = {0, 1, 0, -1, 0, 0};
  static int[] dz = {0, 0, 0, 0, 1, -1};
  static Queue<int[]> q = new LinkedList<>();

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());

    tomato = new int[N][M][H];

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < N; j++) {
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < M; k++) {
          tomato[j][k][i] = Integer.parseInt(st.nextToken());
          if (tomato[j][k][i] == 1) {
            q.add(new int[]{j, k, i, 0});
          }
        }
      }
    }
  }

  static void bfs() {
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int cx = cur[0];
      int cy = cur[1];
      int cz = cur[2];
      int days = cur[3];

      for (int i = 0; i < 6; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        int nz = cz + dz[i];

        if (nx < 0 || ny < 0 || nz < 0 || nx > N - 1 || ny > M - 1 || nz > H - 1) {
          continue;
        }

        if (tomato[nx][ny][nz] == -1) {
          continue;
        }

        if (tomato[nx][ny][nz] > 0) {
          continue;
        }
        tomato[nx][ny][nz] = days + 1;
        q.add(new int[]{nx, ny, nz, days + 1});
        answer = Math.max(answer, days + 1);
      }
    }
  }

  static void pro() {
    answer = 0;
    bfs();

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < M; k++) {
          if (tomato[j][k][i] == 0) {
            answer = -1;
          }
        }
      }
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}