
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

  static int N, M, K;
  static Map<List<Integer>, Integer> map = new HashMap<>();
  static Map<Integer, Integer> countMap = new HashMap<>();

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      int zeroCount = 0;
      List<Integer> list = new ArrayList<>();
      for (int j = 0; j < M; j++) {
        int num = line.charAt(j) - '0';
        if (num == 0) {
          zeroCount++;
          list.add(j);
        }
      }
      map.put(list, map.getOrDefault(list, 0) + 1);
      int value = map.get(list);
      if (countMap.containsKey(zeroCount) && value > countMap.get(zeroCount)) {
        countMap.put(zeroCount, value);
      } else if (!countMap.containsKey(zeroCount)) {
        countMap.put(zeroCount, value);
      }
    }

    K = Integer.parseInt(br.readLine());
  }

  static void pro() {
    boolean isOdd = K % 2 == 1;
    int result = 0;

    for (int key : countMap.keySet()) {
      if (key > K) {
        continue;
      }

      if (isOdd) {
        if (key % 2 == 0) {
          continue;
        }
        result = Math.max(result, countMap.get(key));
      } else {
        if (key % 2 == 1) {
          continue;
        }
        result = Math.max(result, countMap.get(key));
      }
    }
    System.out.println(result);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}