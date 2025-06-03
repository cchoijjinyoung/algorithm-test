import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static long answer;
  static int Q;
  static Map<String, PriorityQueue<Integer>> map;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    Q = Integer.parseInt(st.nextToken());
    map = new HashMap<>();
    for (int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      String name = st.nextToken();
      if (!map.containsKey(name)) {
        map.put(name, new PriorityQueue<>((i1, i2) -> i2 - i1));
      }

      PriorityQueue<Integer> pq = map.get(name);
      if (type == 1) {
        int k = Integer.parseInt(st.nextToken());
        for (int j = 0; j < k; j++) {
          pq.offer(Integer.parseInt(st.nextToken()));
        }
      } else if (type == 2) {
        int b = Integer.parseInt(st.nextToken());
        while (b-- > 0 && !pq.isEmpty()) {
          answer += pq.poll();
        }
      }
      map.put(name, pq);
    }
    System.out.println(answer);
  }
}
