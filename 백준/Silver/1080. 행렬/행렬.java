
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 행렬 : 실버1
 */
public class Main {
  static int N, M;
  static int[][] A, B;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    A = new int[N][M];
    B = new int[N][M];

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        A[i][j] = line.charAt(j) - '0';
      }
    }

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        B[i][j] = line.charAt(j) - '0';
      }
    }
  }

  static void reverse(int x, int y) {
    for (int i = x; i <= x + 2; i++) {
      for (int j = y; j <= y + 2; j++) {
        if (A[i][j] == 1) {
          A[i][j] = 0;
        } else {
          A[i][j] = 1;
        }
      }
    }
  }

  static void pro() {
    int answer = 0;
    // 현재 A좌표와 B좌표의 값이 다르면, 뒤집어야함.
    // 현재 좌표의 x + 2와 y + 2가 좌표를 벗어나면 안됨
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (i + 2 > N - 1 || j + 2 > M - 1) {
          continue;
        }

        if (A[i][j] != B[i][j]) {
          reverse(i, j);
          answer++;
        }
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (A[i][j] != B[i][j]) {
          System.out.println(-1);
          return;
        }
      }
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}