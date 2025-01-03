
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] arr;
  static int A, B;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    int min = 2000000000;
    int L = 0, R = N - 1;
    while (L < R) {
      int sum = arr[L] + arr[R];
      int diff = Math.abs(sum);
      if (min > diff) {
        min = diff;
        A = arr[L];
        B = arr[R];
      }
      if (sum > 0) {
        R--;
      } else if (sum < 0) {
        L++;
      } else {
        System.out.println(arr[L] + " " + arr[R]);
        return;
      }
    }
    System.out.println(A + " " + B);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}