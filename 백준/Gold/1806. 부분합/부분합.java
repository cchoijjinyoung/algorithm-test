
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, S;
  static int[] arr;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());
    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    int result = Integer.MAX_VALUE;
    int sum = 0;
    int L = 0; int R = 0;
    while (true) {
      if (sum >= S) {
        result = Math.min(result, R - L);
        sum -= arr[L++];
      } else if (R == N) {
        break;
      } else {
        sum += arr[R++];
      }
    }

    if (result == Integer.MAX_VALUE) {
      System.out.println(0);
    } else {
      System.out.println(result);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}