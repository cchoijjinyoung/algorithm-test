
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, K, R;
  static boolean[][] board;
  static List<int[]> cowPoints = new ArrayList<>();
  static Set<Road> roadSet = new HashSet<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    board = new boolean[N][N];

    for (int i = 0; i < R; i++) {
      st = new StringTokenizer(br.readLine());
      int r1 = Integer.parseInt(st.nextToken()) - 1;
      int c1 = Integer.parseInt(st.nextToken()) - 1;
      int r2 = Integer.parseInt(st.nextToken()) - 1;
      int c2 = Integer.parseInt(st.nextToken()) - 1;
      roadSet.add(new Road(r1, c1, r2, c2));
      roadSet.add(new Road(r2, c2, r1, c1));
    }

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      cowPoints.add(new int[]{r, c});
      board[r][c] = true;
    }
  }

  static void pro() {
    int answer = 0;
    for (int[] point : cowPoints) {
      answer += bfs(point);
    }
    System.out.println(answer / 2);
  }

  static int bfs(int[] point) {
    int result = 0;

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    boolean[][] visited = new boolean[N][N];
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{point[0], point[1]});
    visited[point[0]][point[1]] = true;
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int cx = cur[0];
      int cy = cur[1];

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        // 다음 위치가 밖이면,
        if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) {
          continue;
        }

        // 길이 있다면,
        if (roadSet.contains(new Road(cx, cy, nx, ny))) {
          continue;
        }

        // 이미 방문한 곳이면,
        if (visited[nx][ny]) {
          continue;
        }

        // 위치가 소라면,
        if (board[nx][ny]) {
          result++;
        }

        q.add(new int[]{nx, ny});
        visited[nx][ny] = true;
      }
    }
    // result = 현재 소가 길 없이 만날 수 있는 소
    // 길을 건너야 만날 수 있는 소 = K - result - 1(본인)
    return K - result - 1;
  }

  static class Road {
    int r1;
    int c1;
    int r2;
    int c2;

    Road(int r1, int c1, int r2, int c2) {
      this.r1 = r1;
      this.c1 = c1;
      this.r2 = r2;
      this.c2 = c2;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Road road = (Road) o;
      return r1 == road.r1 && c1 == road.c1 && r2 == road.r2 && c2 == road.c2;
    }

    @Override
    public int hashCode() {
      return Objects.hash(r1, c1, r2, c2);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}