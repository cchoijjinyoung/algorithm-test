
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ATM : 실버4
 * !아이디어
 * : 정렬
 */
public class Main {
  static int N;
  static int[] A;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    A = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    Arrays.sort(A);
    int answer = A[0];
    for (int i = 1; i < N; i++) {
      A[i] = A[i - 1] + A[i];
      answer += A[i];
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}