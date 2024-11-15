
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] A, B;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    A = new int[N];
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    B = new int[M];
    for (int i = 0; i < M; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void binarySearch(int number) {
    // A 배열에서 이분 탐색
    int L = 0; int R = A.length - 1;

    while (L <= R) {
      int mid = (L + R) / 2;

      if (A[mid] == number) {
        System.out.println(1);
        return;
      } else if (A[mid] > number) {
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    System.out.println(0);
  }

  static void pro() {
    // 이분 탐색
    Arrays.sort(A);
    for (int j : B) {
      binarySearch(j);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}