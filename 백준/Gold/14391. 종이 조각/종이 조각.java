
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] board;
  static int answer;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][M];

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        board[i][j] = line.charAt(j) - '0';
      }
    }
  }

  static void bitMask() {
    for (int bit = 0; bit < Math.pow(2, N * M); bit++) {
      int sum = 0;
      // 가로
      for (int i = 0; i < N; i++) {
        int number = 0;
        for (int j = 0; j < M; j++) {
          int seq = i * M + j;
          if ((bit & (1 << seq)) == 0) {
            number *= 10;
            number += board[i][j];
          } else {
            sum += number;
            number = 0;
          }
        }
        sum += number;
      }

      // 세로
      for (int i = 0; i < M; i++) {
        int number = 0;
        for (int j = 0; j < N; j++) {
          int seq = j * M + i;
          if ((bit & (1 << seq)) != 0) {
            number *= 10;
            number += board[j][i];
          } else {
            sum += number;
            number = 0;
          }
        }
        sum += number;
      }
      answer = Math.max(answer, sum);
    }
  }

  static void pro() {
    bitMask();
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}