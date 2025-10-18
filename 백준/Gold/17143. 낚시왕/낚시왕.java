
import java.io.*;
import java.util.*;

public class Main {
  static int R, C, M;
  static Shark[][] map;
  static int answer = 0;

  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, 1, -1};

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new Shark[R + 1][C + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());
      map[r][c] = new Shark(s, d, z);
    }
  }

  static void pro() {
    for (int col = 1; col <= C; col++) {
      for (int row = 1; row <= R; row++) {
        if (map[row][col] != null) {
          answer += map[row][col].z;
          map[row][col] = null;
          break;
        }
      }

      Shark[][] next = new Shark[R + 1][C + 1];

      for (int r = 1; r <= R; r++) {
        for (int c = 1; c <= C; c++) {
          if (map[r][c] == null) continue;

          Shark shark = map[r][c];
          int[] nextPos = move(r, c, shark);
          int nr = nextPos[0], nc = nextPos[1];
          int nd = nextPos[2];

          // 방향 업데이트
          shark.d = nd;

          // 겹칠 때 큰 상어만 남기기
          if (next[nr][nc] == null || next[nr][nc].z < shark.z) {
            next[nr][nc] = shark;
          }
        }
      }

      map = next;
    }

    System.out.println(answer);
  }

  static int[] move(int r, int c, Shark shark) {
    int s = shark.s;
    int d = shark.d;

    if (d == 1 || d == 2) s %= (R - 1) * 2;
    else s %= (C - 1) * 2;

    int nr = r;
    int nc = c;

    for (int i = 0; i < s; i++) {
      int tr = nr + dr[d];
      int tc = nc + dc[d];

      if (tr < 1 || tr > R || tc < 1 || tc > C) {
        if (d == 1) d = 2;
        else if (d == 2) d = 1;
        else if (d == 3) d = 4;
        else if (d == 4) d = 3;

        tr = nr + dr[d];
        tc = nc + dc[d];
      }

      nr = tr;
      nc = tc;
    }

    return new int[]{nr, nc, d};
  }

  static class Shark {
    int s, d, z;

    Shark(int s, int d, int z) {
      this.s = s;
      this.d = d;
      this.z = z;
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}
