
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 수 고르기 : 골드5
 */
public class Main {
  static int N, M;
  static int[] A;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    A = new int[N];

    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(br.readLine());
    }
  }

  static void pro() {
    Arrays.sort(A);

    int answer = Integer.MAX_VALUE;
    int L = 0; int R = 1;

    while (L <= R && R < N) {
      int diff = A[R] - A[L];
      if (diff >= M) {
        answer = Math.min(answer, diff);
        L++;
      } else {
        R++;
      }
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}