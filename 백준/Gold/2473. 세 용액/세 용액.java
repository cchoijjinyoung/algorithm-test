import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
  static int N;
  static int[] A;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    A = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    long min = Long.MAX_VALUE;
    Arrays.sort(A, 1, N + 1);

    int v1 = 0, v2 = 0, v3 = 0;
    // target을 선정해줄 것이다.
    for (int i = 1; i <= N - 2; i++) {
      int target = -A[i];

      // target이 선정되면, 그 옆은 L, R은 끝
      int L = i + 1, R = N;
      while (L < R) { // L과 R이 겹치면 안되니까, == 는 빼준다.
        long sum = A[L] + A[R];

        if (min > Math.abs(sum - target)) {
          min = Math.abs(sum - target);
          v1 = -target;
          v2 = A[L];
          v3 = A[R];
        }

        if (sum > target) R--;
        else L++;
      }
    }
    System.out.println(v1 + " " + v2 + " " + v3);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}