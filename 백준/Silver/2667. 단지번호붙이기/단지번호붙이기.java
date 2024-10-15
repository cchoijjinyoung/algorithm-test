
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
  static int N;
  static int[][] graph;
  static boolean[][] visited;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static List<Integer> counts = new ArrayList<>();

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    graph = new int[N][N];
    visited = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      String row = br.readLine();
      for (int j = 0; j < N; j++) {
        graph[i][j] = row.charAt(j) - '0';
      }
    }
  }

  static int bfs(int r, int c) {
    int result = 0;
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{r, c});
    visited[r][c] = true;
    result++;

    while (!q.isEmpty()) {
      int[] cur = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cur[0] + dx[i];
        int ny = cur[1] + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) {
          continue;
        }

        if (graph[nx][ny] == 0) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        q.offer(new int[]{nx, ny});
        visited[nx][ny] = true;
        result++;
      }
    }
    return result;
  }

  static void pro() {
    int answer = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (visited[i][j]) continue;
        if (graph[i][j] == 0) continue;
        int apartmentCount = bfs(i, j);
        if (apartmentCount > 0) {
          answer++;
          counts.add(apartmentCount);
        }
      }
    }
    Collections.sort(counts);
    System.out.println(answer);
    for (int count : counts) {
      System.out.println(count);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}