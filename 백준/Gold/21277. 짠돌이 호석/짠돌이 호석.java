
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 짠돌이 호석, 골드3 퍼즐 이동, 회전
 */
public class Main {

  static int N1, N2, M1, M2;
  static int[][] p1, p2;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N1 = Integer.parseInt(st.nextToken());
    M1 = Integer.parseInt(st.nextToken());

    p1 = new int[N1][M1];
    for (int i = 0; i < N1; i++) {
      String line = br.readLine();
      for (int j = 0; j < M1; j++) {
        p1[i][j] = line.charAt(j) - '0';
      }
    }

    st = new StringTokenizer(br.readLine());
    N2 = Integer.parseInt(st.nextToken());
    M2 = Integer.parseInt(st.nextToken());
    p2 = new int[N2][M2];
    for (int i = 0; i < N2; i++) {
      String line = br.readLine();
      for (int j = 0; j < M2; j++) {
        p2[i][j] = line.charAt(j) - '0';
      }
    }
  }

  static void turn() {
    int[][] result = new int[M2][N2];

    for (int i = 0; i < M2; i++) {
      for (int j = 0; j < N2; j++) {
        result[i][j] = p2[j][M2 - 1 - i];
      }
    }
    p2 = result;
    int temp = M2;
    M2 = N2;
    N2 = temp;
  }

  static void p2Print() {
    for (int i = 0; i < p2.length; i++) {
      for (int j = 0; j < p2[0].length; j++) {
        System.out.print(p2[i][j]);
      }
      System.out.println();
    }
  }

  static boolean possible(int shift_row, int shift_col) {
    for (int br = 0; br < p2.length; br++) {
      for (int bc = 0; bc < p2[0].length; bc++) {
        if (p2[br][bc] == 0) {
          continue;
        }
        int ar = br + shift_row;
        int ac = bc + shift_col;

        if (ar < 0 || ac < 0 || ar > N1 - 1 || ac > M1 - 1) {
          continue;
        }

        if (p1[ar][ac] == 1) {
          return false;
        }
      }
    }
    return true;
  }

  static void pro() {
    int min = Integer.MAX_VALUE;
    // p2 회전
    for (int i = 0; i < 4; i++) {
      turn();
      // 평행이동
      for (int shift_row = -50; shift_row <= 50; shift_row++) {
        for (int shift_col = -50; shift_col <= 50; shift_col++) {
          if (possible(shift_row, shift_col)) {
            int fr = Math.max(N1, N2 + shift_row) - Math.min(0, shift_row);
            int fc = Math.max(M1, M2 + shift_col) - Math.min(0, shift_col);
            min = Math.min(min, fr * fc);
          }
        }
      }
    }
    System.out.println(min);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}