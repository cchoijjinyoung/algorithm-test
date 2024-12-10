
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 전구와 스위치
 */
public class Main {
  static int N;
  static int[] A, B;
  static int[] firstPressA;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    A = new int[N]; // 기존 전구 상태
    B = new int[N]; // 만들고자하는 전구 상태
    String line = br.readLine();
    for (int i = 0; i < N; i++) {
      A[i] = line.charAt(i) - '0';
    }

    line = br.readLine();
    for (int i = 0; i < N; i++) {
      B[i] = line.charAt(i) - '0';
    }
  }

  static int update(int value) {
    if (value == 0) return 1;
    return 0;
  }

  static boolean isSame(int[] A, int[] B) {
    for (int i = 0; i < N; i++) {
      if (A[i] != B[i]) {
        return false;
      }
    }
    return true;
  }

  static void pro() {
    firstPressA = Arrays.copyOf(A, N); // 첫번째 스위치를 누른 전구 상태
    firstPressA[0] = update(firstPressA[0]);
    firstPressA[1] = update(firstPressA[1]);
    int firstPressCount = 1;
    int count = 0;

    for (int i = 0; i < N - 1; i++) {
      int switch_idx = i + 1;
      if (A[i] != B[i]) {
        count++;
        A[switch_idx - 1] = update(A[switch_idx - 1]);
        A[switch_idx] = update(A[switch_idx]);

        if (switch_idx < N - 1) {
          A[switch_idx + 1] = update(A[switch_idx + 1]);
        }
      }

      if (firstPressA[i] != B[i]) {
        firstPressCount++;
        firstPressA[switch_idx - 1] = update(firstPressA[switch_idx - 1]);
        firstPressA[switch_idx] = update(firstPressA[switch_idx]);

        if (switch_idx < N - 1) {
          firstPressA[switch_idx + 1] = update(firstPressA[switch_idx + 1]);
        }
      }
    }

    if (isSame(A, B) && isSame(firstPressA, B)) {
      System.out.println(Math.min(count, firstPressCount));
    } else if (isSame(A, B)) {
      System.out.println(count);
    } else if (isSame(firstPressA, B)) {
      System.out.println(firstPressCount);
    } else {
      System.out.println(-1);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}