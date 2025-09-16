
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static Map<String, Integer> map = new HashMap<>();
  static PriorityQueue<Item> pq = new PriorityQueue<>(
      (o1, o2) -> {
        int cmp = Integer.compare(o1.phase, o2.phase);
        if (cmp == 0) {
          return o1.name.compareTo(o2.name);
        }
        return cmp;
      }
  );
  static Map<String, List<String>> graph = new HashMap<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      String A = st.nextToken();
      String B = st.nextToken();

      map.putIfAbsent(A, 0);
      map.put(B, map.getOrDefault(B, 0) + 1);

      graph.putIfAbsent(A, new ArrayList<>());
      graph.putIfAbsent(B, new ArrayList<>());
      List<String> list = graph.get(A);
      list.add(B);
      graph.put(A, list);
    }
  }

  static void pro() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      int value = entry.getValue();

      if (value == 0) {
        pq.add(new Item(key, 0));
      }
    }

    int printCount = 0;

    while (!pq.isEmpty()) {
      Item cur = pq.poll();
      String name = cur.name;
      int phase = cur.phase;
      sb.append(name).append('\n');
      printCount++;

      for (String next : graph.get(name)) {
        Integer count = map.put(next, map.get(next) - 1);
        if (count == 1) {
          pq.add(new Item(next, phase + 1));
        }
      }
    }

    if (printCount != map.size()) {
      System.out.println(-1);
    } else {
      System.out.println(sb);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }

  static class Item {
    String name;
    int phase;

    Item(String name, int phase) {
      this.name = name;
      this.phase = phase;
    }
  }
}