
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static Set<Integer> set;
  static int[] arr;
  static Map<Integer, Integer> map = new HashMap<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    set = new HashSet<>();
    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int n = Integer.parseInt(st.nextToken());
      set.add(n);
      arr[i] = n;
    }
  }

  static void pro() {
    for (int number : set) {
      map.put(number, 0);
    }

    for (int number : set) {
      for (int i = 1; i * i <= number; i++) {
        if (number % i == 0) {
          int mok = number / i;

          if (set.contains(i)) {
            map.put(i, map.get(i) + 1);
            map.put(number, map.get(number) - 1);
          }

          if (i != mok && set.contains(mok)) {
            map.put(mok, map.get(mok) + 1);
            map.put(number, map.get(number) - 1);
          }
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int number : arr) {
      sb.append(map.get(number)).append(" ");
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}