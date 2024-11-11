
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int Q;
  static long answer;
  static Map<String, PriorityQueue<Integer>> map;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    Q = Integer.parseInt(br.readLine());
    map = new HashMap<>();

    for (int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      String name = st.nextToken();

      if (type == 1) {
        if (!map.containsKey(name)) {
          map.put(name, new PriorityQueue<>((o1, o2) -> o2 - o1));
        }

        int k = Integer.parseInt(st.nextToken());
        for (int j = 0; j < k; j++) {
          int number = Integer.parseInt(st.nextToken());
          map.get(name).add(number);
        }
      }
      else {
        int count = Integer.parseInt(st.nextToken());
        if (map.containsKey(name)) {
          PriorityQueue<Integer> pq = map.get(name);
          int size = pq.size();
          count = Math.min(count, size);

          while (count-- > 0 && !pq.isEmpty()) {
            answer += pq.poll();
          }
        }
      }
    }
  }

  static void pro() {
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}