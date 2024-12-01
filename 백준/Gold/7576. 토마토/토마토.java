
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 토미토 : 골드 5
 *
 * ! 아이디어
 * 멀티 소스 BFS
 * 시간복잡도 1000 * 1000 * 4
 */
public class Main {
  static int M, N;
  static int[][] board;
  static Queue<Integer> q = new LinkedList<>();
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int number = Integer.parseInt(st.nextToken());
        if (number == 1) {
          q.add(i);
          q.add(j);
          q.add(1);
        }
        board[i][j] = number;
      }
    }
  }

  static void pro() {
    int answer = 0;
    while (!q.isEmpty()) {
      int cx = q.poll();
      int cy = q.poll();
      int day = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
          continue;
        }

        if (board[nx][ny] != 0) {
          continue;
        }

        q.add(nx);
        q.add(ny);
        q.add(day + 1);
        board[nx][ny] = day + 1;
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] == 0) {
          System.out.println(-1);
          return;
        }
        answer = Math.max(answer, board[i][j]);
      }
    }
    System.out.println(answer - 1);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}