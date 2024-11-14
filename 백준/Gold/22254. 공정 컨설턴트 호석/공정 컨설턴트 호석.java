
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, X;
  static int[] A;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());

    A = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  static boolean check(int count) {
    int max = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < count; i++) {
      pq.add(0);
    }

    int idx = 0;
    while (idx < N) {
      int cur = pq.poll();
      pq.add(cur + A[idx++]);
    }

    while (!pq.isEmpty()) {
      max = Math.max(max, pq.poll());
    }

    return max <= X;
  }

  static void pro() {
    int L = 1; int R = N; // 최대 공정 갯수가 N이기 때문

    while (L <= R) {
      int mid = (L + R) / 2;
      if (!check(mid)) { // 현재 mid 값으로 X 시간 안에 가능해? 안되면 L = mid + 1;
        L = mid + 1;
      } else {
        R = mid - 1;
      }
    }
    System.out.println(L);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}