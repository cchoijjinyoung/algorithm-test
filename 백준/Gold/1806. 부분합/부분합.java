
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부분합 골드4
 * 투 포인터
 */
class Main {
  static int N;
  static int S;
  static int[] A;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    A = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    int R = 0; int sum = 0; int answer = N + 1;

    for (int L = 1; L <= N; L++) {
      sum -= A[L - 1];

      while (R + 1 <= N && sum < S) {
        R++;
        sum += A[R];
      }

      if (sum >= S) {
        answer = Math.min(answer, R - L + 1);
      }
    }
    if (answer == N + 1) {
      answer = 0;
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}