
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static boolean[] isPrime;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    isPrime = new boolean[N + 1];
  }

  static void pro() {
    Arrays.fill(isPrime, true);
    isPrime[0] = false;
    isPrime[1] = false;

    for (int i = 2; i <= Math.sqrt(N); i++) {
      if (!isPrime[i]) continue;

      for (int j = i * i; j <= N; j += i) {
        isPrime[j] = false;
      }
    }

    List<Integer> list = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      if (isPrime[i]) {
        list.add(i);
      }
    }

    int result = 0;

    int L = 0; int R = 0;
    int sum = 0;

    while (L <= R) {
      if (sum == N) {
        result++;
        sum -= list.get(L++);
      } else if (sum > N) {
        sum -= list.get(L++);
      } else {
        if (R == list.size()) break;
        sum += list.get(R++);
      }
    }
    System.out.println(result);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}