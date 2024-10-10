import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
  static int N;
  static Map<Long, Integer> tm;
  static int max = Integer.MIN_VALUE;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    tm = new TreeMap<>();

    for (int i = 0; i < N; i++) {
      long x = Long.parseLong(br.readLine());
      tm.put(x, tm.getOrDefault(x, 0) + 1);
      max = Math.max(max, tm.get(x));
    }
  }

  public static void main(String[] args) throws Exception {
    input();

    long answer = 0;
    for (Map.Entry<Long, Integer> entry : tm.entrySet()) {
      if (entry.getValue() == max) {
        answer = entry.getKey();
        break;
      }
    }
    System.out.println(answer);
  }
}