
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[][] board;
  static int min = Integer.MAX_VALUE;
  static List<int[]> virusList = new ArrayList<>();
  static boolean[] visited;
  static int blankCount;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int number = Integer.parseInt(st.nextToken());
        if (number == 2) {
          virusList.add(new int[]{i, j});
          blankCount++;
        } else if (number == 0) {
          blankCount++;
        }
        board[i][j] = number;
      }
    }
    visited = new boolean[virusList.size()];
  }

  static void dfs(int[] arr, int idx, int depth) {
    if (depth == M) {
      bfs(arr);
      return;
    }

    for (int i = idx; i < virusList.size(); i++) {
      if (visited[i]) {
        continue;
      }
      arr[depth] = i;
      visited[i] = true;
      dfs(arr, i + 1, depth + 1);
      visited[i] = false;
    }
  }

  static void bfs(int[] arr) {
    int max = 0;
    int virusCount = 0;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    boolean[][] visited = new boolean[N][N];

    Queue<int[]> q = new LinkedList<>();
    for (int idx : arr) {
      int[] point = virusList.get(idx);
      int x = point[0];
      int y = point[1];
      q.add(new int[]{x, y, 0});
      visited[x][y] = true;
    }

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cx = info[0];
      int cy = info[1];
      int time = info[2];
      max = Math.max(max, time);
      virusCount++;

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) {
          continue;
        }

        if (visited[nx][ny]) continue;

        if (board[nx][ny] == 1) continue;

        visited[nx][ny] = true;
        q.add(new int[]{nx, ny, time + 1});
      }
    }

    if (virusCount < blankCount) {
      return;
    }

    min = Math.min(min, max);
  }

  static void pro() {
    // 바이러스 위치 선정(dfs)
    dfs(new int[M], 0, 0);
    System.out.println(min == Integer.MAX_VALUE ? -1 : min);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}