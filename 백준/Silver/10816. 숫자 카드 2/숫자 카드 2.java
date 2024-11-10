
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] A;
  static Map<Integer, Integer> map = new HashMap();

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int number = Integer.parseInt(st.nextToken());
      map.put(number, map.getOrDefault(number, 0) + 1);
    }

    M = Integer.parseInt(br.readLine());
    A = new int[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    StringBuilder sb = new StringBuilder();
    for (int key : A) {
      if (map.containsKey(key)) {
        sb.append(map.get(key)).append(' ');
      } else {
        sb.append(0).append(' ');
      }
    }
    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}