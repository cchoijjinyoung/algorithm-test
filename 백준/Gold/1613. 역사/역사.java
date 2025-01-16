
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, K, S;
  static boolean[][] board;
  static int[][] queries;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    board = new boolean[N + 1][N + 1];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      board[from][to] = true;
    }

    S = Integer.parseInt(br.readLine());
    queries = new int[S][2];
    for (int i = 0; i < S; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      queries[i] = new int[]{from, to};
    }
  }

  static void pro() {
    for (int k = 1; k <= N; k++) {
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          if (i == j) continue;
          if (board[i][k] && board[k][j]) {
            board[i][j] = true;
          }
        }
      }
    }

    for (int[] query : queries) {
      int from = query[0];
      int to = query[1];
      if (board[from][to]) {
        System.out.println(-1);
      } else if (board[to][from]) {
        System.out.println(1);
      } else {
        System.out.println(0);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}