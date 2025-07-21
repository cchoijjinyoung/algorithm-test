
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  static int T, N, M;
  static int[] A, B;
  static Map<Integer, Integer> mapA = new HashMap<>();
  static Map<Integer, Integer> mapB = new HashMap<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    T = Integer.parseInt(br.readLine());

    N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    A = new int[N];
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    B = new int[M];
    for (int i = 0; i < M; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    long result = 0;
    // A배열 순회해서 합을 맵에 담기
    initialize(A, mapA);
    // B배열 순회해서 합을 맵에 담기
    initialize(B, mapB);

    for (int key : mapA.keySet()) {
      int target = T - key;
      if (mapB.containsKey(target)) {
        result += (long) mapA.get(key) * mapB.get(target);
      }
    }
    System.out.println(result);
  }

  // 배열을 순회해서 맵에 담기
  static void initialize(int[] arr, Map<Integer, Integer> map) {
    for (int i = 0; i < arr.length; i++) {
      int sum = 0;
      for (int j = i; j < arr.length; j++) {
        sum += arr[j];
        map.put(sum, map.getOrDefault(sum, 0) + 1);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}