import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 나무자르기 실버2
 * 이분 탐색, 매개 변수 탐색
 */
class Main {
  static int N;
  static int M;
  static int[] A;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    A = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  public static void main(String[] args) throws Exception {
    input();

    // 이분 탐색으로 높이를 찾는다. logN
    // N개의 나무를 순회하면서 해당 높이에서 몇미터의 나무를 가져갈 수 있는 지 더한다.(N)
    // N logN
    Arrays.sort(A, 1, N + 1);

    int L = 0; int R = A[N];
    while (L <= R) {
      int mid = (L + R) / 2;
      long result = 0;
      for (int i = 1; i <= N; i++) {
        if (A[i] < mid) {
          continue;
        }
        result += A[i] - mid;
      }

      if (result >= M) {
        L = mid + 1;
      } else {
        R = mid - 1;
      }
    }
    System.out.println(L - 1);
  }
}