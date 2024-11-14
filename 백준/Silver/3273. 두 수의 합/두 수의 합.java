
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, X;
  static int[] A;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    A = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    X = Integer.parseInt(br.readLine());
  }

  static void pro() {
    int answer = 0;
    Arrays.sort(A);
    int L = 0;
    int R = N - 1;

    while (L < R) {
      int sum = A[L] + A[R];
      if (sum == X) {
        answer++;
        R--;
      } else if (sum > X) R--;
      else L++;
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}