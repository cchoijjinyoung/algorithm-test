
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 테트로미노 : 골드 5
 * 아이디어 : 뒤집을 때마다 현재 상태 업데이트
 * 현재 상태에 따라 L, R을 이동 후, L~R 사이 출력
 * L > R 인경 우, D 함수를 수행할 수 없음 -> "error"
 */
public class Main {
  static int T;
  static boolean isReverse;
  static int N, L, R;
  static char[] P;
  static int[] A;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      String line = br.readLine();
      P = line.toCharArray();

      N = Integer.parseInt(br.readLine());
      A = new int[N];

      line = br.readLine();
      line = line.replace("[", "");
      line = line.replace("]", "");
      String[] temp = line.split(",");
      for (int k = 0; k < N; k++) {
        A[k] = Integer.parseInt(temp[k]);
      }
      pro();
    }
  }

  static void pro() {
    L = 0; R = N - 1;
    isReverse = false;

    for (char function : P) {
      if (function == 'R') {
        isReverse = !isReverse;
        continue;
      }

      if (function == 'D') {
        if (L > R) {
          System.out.println("error");
          return;
        }
        if (isReverse) R--;
        else L++;
      }
    }
    StringBuilder sb = new StringBuilder();
    sb.append('[');

    if (!isReverse) {
      for (int i = L; i <= R; i++) {
        sb.append(A[i]);

        if (i != R) {
          sb.append(',');
        }
      }
    } else {
      for (int i = R; i >= L; i--) {
        sb.append(A[i]);

        if (i != L) {
          sb.append(',');
        }
      }
    }

    sb.append(']');
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}