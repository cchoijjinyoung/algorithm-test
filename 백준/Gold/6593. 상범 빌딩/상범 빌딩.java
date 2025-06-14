
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int L, R, C;
  static char[][][] building;
  static boolean[][][] visited;
  static int[] S, E;
  static int[] dx = {1, -1, 0, 0, 0, 0};
  static int[] dy = {0, 0, 1, -1, 0, 0};
  static int[] dz = {0, 0, 0, 0, 1, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    while (true) {
      st = new StringTokenizer(br.readLine());
      L = Integer.parseInt(st.nextToken());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      building = new char[L][R][C];
      if (L == 0 && R == 0 && C == 0) break;

      for (int i = 0; i < L; i++) {
        for (int j = 0; j < R; j++) {
          String line = br.readLine();
          for (int k = 0; k < C; k++) {
            char c = line.charAt(k);
            building[i][j][k] = c;
            if (c == 'S') {
              S = new int[]{i, j, k};
            } else if (c == 'E') {
              E = new int[]{i, j, k};
            }
          }
        }
        br.readLine();
      }
      bfs();
    }
  }

  static void bfs() {
    visited = new boolean[L][R][C];

    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{S[0], S[1], S[2], 0});
    visited[S[0]][S[1]][S[2]] = true;

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int cz = cur[0];
      int cx = cur[1];
      int cy = cur[2];

      int move = cur[3];
      if (building[cz][cx][cy] == 'E') {
        System.out.println("Escaped in " + move +  " minute(s).");
        return;
      }

      for (int i = 0; i < 6; i++) {
        int nz = cz + dz[i];
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nz < 0 || nx < 0 || ny < 0 || nz > L - 1 || nx > R - 1 || ny > C - 1) {
          continue;
        }

        if (building[nz][nx][ny] == '#') {
          continue;
        }

        if (visited[nz][nx][ny]) continue;

        q.add(new int[]{nz, nx, ny, move + 1});
        visited[nz][nx][ny] = true;
      }
    }

    System.out.println("Trapped!");
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}