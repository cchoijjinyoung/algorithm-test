import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] board;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    board = new int[N][M];

    for (int i = 0; i < N; i++) {
      String[] line = br.readLine().split("");
      for (int j = 0; j < M; j++) {
        int num = Integer.parseInt(line[j]);
        board[i][j] = num;
      }
    }
  }

  static void dijkstra() {
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
    boolean[][] visited = new boolean[N][M];
    pq.add(new int[]{0, 0, 0});
    while (!pq.isEmpty()) {
      int[] info = pq.poll();
      int cx = info[0];
      int cy = info[1];
      int breakCount = info[2];

      if (cx == N - 1 && cy == M - 1) {
        System.out.println(breakCount);
        return;
      }

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }
        visited[nx][ny] = true;
        if (board[nx][ny] == 0) {
          pq.add(new int[]{nx, ny, breakCount});
        } else {
          pq.add(new int[]{nx, ny, breakCount + 1});
        }
      }
    }
  }

  static void pro() {
    dijkstra();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}