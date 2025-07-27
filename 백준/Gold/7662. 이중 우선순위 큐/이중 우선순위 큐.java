
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
  static int T;
  static int k;
  static TreeMap<Integer, Integer> map;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    StringTokenizer st;
    while (T-- > 0) {
      k = Integer.parseInt(br.readLine());
      map = new TreeMap<>();

      for (int i = 0; i < k; i++) {
        st = new StringTokenizer(br.readLine());
        char op = st.nextToken().charAt(0);
        int num = Integer.parseInt(st.nextToken());

        if (op == 'I') {
          map.put(num, map.getOrDefault(num, 0) + 1);
          continue;
        }

        if (op == 'D') {
          if (map.isEmpty()) {
            continue;
          }

          int key = (num == 1) ? map.lastKey() : map.firstKey();
          int count = map.get(key);

          if (count == 1) {
            map.remove(key);
          } else {
            map.put(key, count - 1);
          }
        }
      }
      pro();
    }
  }

  static void pro() {
    if (map.isEmpty()) {
      System.out.println("EMPTY");
    } else {
      System.out.println(map.lastKey() + " " + map.firstKey());
    }
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}