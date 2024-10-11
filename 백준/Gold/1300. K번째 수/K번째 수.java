
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static int N;
  static int K;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());
  }

  static boolean check(long mid) {
    // mid 보다 작은 숫자가 몇개 있냐?
    int cnt = 0;
    for (int i = 1; i <= N; i++) {
      cnt += (int) Math.min(mid / i, N);
    }
    return cnt >= K;
  }

  public static void main(String[] args) throws Exception {
    input();
    long L = 0; long R = K;

    while (L <= R) {
      long mid = (L + R) / 2; // i * j
      if (check(mid)) {
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    System.out.println(L);
  }
}