
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static Map<Integer, Integer> map;
  static int[] sum;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    map = new HashMap<>();
    sum = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    map.put(0, 1);
    long answer = 0;
    for (int j = 1; j <= N; j++) {
      answer += map.getOrDefault(sum[j] - K, 0);
      map.put(sum[j], map.getOrDefault(sum[j], 0) + 1);
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}