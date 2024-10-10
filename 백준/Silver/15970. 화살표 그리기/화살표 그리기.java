
import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
  static int N;
  static Map<Integer, List<Integer>> hm;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    hm = new HashMap<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int idx = Integer.parseInt(st.nextToken());
      int color = Integer.parseInt(st.nextToken());
      hm.put(color, hm.getOrDefault(color, new ArrayList<>()));
      hm.get(color).add(idx);
    }
  }

  public static void main(String[] args) throws Exception {
    input();

    int answer = 0;
    for (List<Integer> list : hm.values()) {
      Collections.sort(list);

      for (int i = 0; i < list.size(); i++) {
        if (i == 0) {
          answer += list.get(i + 1) - list.get(i);
        } else if (i == list.size() - 1) {
          answer += list.get(i) - list.get(i - 1);
        } else {
          answer += Math.min(list.get(i + 1) - list.get(i), list.get(i) - list.get(i - 1));
        }
      }
    }

    System.out.println(answer);
  }
}