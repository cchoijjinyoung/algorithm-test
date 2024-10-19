import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 거울 설치, 골드3
 */
public class Main {
  static int N;
  static int[][] map;
  static int[][] doors;
  static int[] dx = {1, 0, -1, 0}; // 하 우 상 좌
  static int[] dy = {0, 1, 0, -1};
  static boolean[][][] visited;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    doors = new int[2][2];
    visited = new boolean[N][N][4];

    int D = 0;
    for (int i = 0; i < N; i++) {
      String s = br.readLine();
      for (int j = 0; j < N; j++) {
        map[i][j] = s.charAt(j);
        if (map[i][j] == '#') {
          doors[D][0] = i;
          doors[D][1] = j;
          D++;
        }
      }
    }
  }

  static void pro() {
    int[] start = doors[0];
    int[] end = doors[1];

    PriorityQueue<int[]> q = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);

    for (int i = 0; i < 4; i++) {
      q.add(new int[]{start[0], start[1], 0, i});
      visited[start[0]][start[1]][i] = true;
    }

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int x = cur[0];
      int y = cur[1];
      int count = cur[2];
      int dir = cur[3];

      if (x == end[0] && y == end[1]) {
        System.out.println(count);
        return;
      }

      int nx = x + dx[dir];
      int ny = y + dy[dir];

      if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) {
        continue;
      }

      if (map[nx][ny] == '*') {
        continue;
      }
        
      if (visited[nx][ny][dir]) continue;
      visited[nx][ny][dir] = true;

      if (map[nx][ny] == '!') {
        // '/' , '\' 으로 거울을 설치해줄수 있다.
        // if (visited[nx][ny][(dir + 1) % 4]) continue;
        // visited[nx][ny][(dir + 1) % 4] = true;
        q.add(new int[]{nx, ny, count + 1, (dir + 1) % 4});
          
        // if (visited[nx][ny][(dir + 3) % 4]) continue;
        // visited[nx][ny][(dir + 3) % 4] = true;
        q.add(new int[]{nx, ny, count + 1, (dir + 3) % 4});
      }
      q.add(new int[]{nx, ny, count, dir});
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}