
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] board;
  static int totalCount;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int num = Integer.parseInt(st.nextToken());
        board[i][j] = num;
        if (num == 1) {
          totalCount++;
        }
      }
    }
    // 입력 0, 1로 받음
    // 치즈 총 개수도 같이 셈
  }

  static void pro() {
    // 치즈가 0이 아니면 계속 돌려줄건데,
    int answer = 0;
    while (totalCount != 0) {
      // 빈공간에서 bfs를 돌려서 만나는 모든 공기 부분을 -1로 바꾼다.
      bfs();
      // board를 순회해서 치즈의 4면 중 2면이 -1이면 해당 치즈를 -1로 바꾸고 count를 센다.
      int removeCount = remove();
      totalCount -= removeCount;
      answer++;
    }
    System.out.println(answer);
  }

  static int remove() {
    List<int[]> list = new ArrayList<>();
    int result = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] != 1) {
          continue;
        }
        int count = 0;
        for (int d = 0; d < 4; d++) {
          if (board[i + dx[d]][j + dy[d]] == -1) {
            count++;
          }
        }
        if (count >= 2) {
          list.add(new int[]{i, j});
          result++;
        }
      }
    }

    for (int[] removePoint : list) {
      int x = removePoint[0];
      int y = removePoint[1];
      board[x][y] = -1;
    }
    return result;
  }

  static void bfs() {
    Queue<int[]> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][M];
    q.add(new int[]{0, 0});
    visited[0][0] = true;

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      for (int i = 0; i < 4; i++) {
        int nx = cur[0] + dx[i];
        int ny = cur[1] + dy[i];

        if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1) {
          continue;
        }

        if (visited[nx][ny]) {
          continue;
        }

        if (board[nx][ny] == 1) {
          continue;
        }

        board[nx][ny] = -1;
        q.add(new int[]{nx, ny});
        visited[nx][ny] = true;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}