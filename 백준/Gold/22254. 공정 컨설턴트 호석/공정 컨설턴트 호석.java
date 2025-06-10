
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, X;
  static int[] times;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());

    times = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      times[i] = Integer.parseInt(st.nextToken());
    }
  }

  static boolean check(int n) {
    Queue<Integer> pq = new PriorityQueue<>((x1, x2) -> x1 - x2);
    for (int i = 0; i < n; i++) {
      pq.offer(times[i]);
    }

    for (int i = n; i < N; i++) {
      int startTime = pq.poll();
      int endTime = startTime + times[i];
      if (endTime > X) {
        return false;
      }
      pq.offer(endTime);
    }
    return true;
  }

  static void pro() {
    // 이분 탐색
    int L = 1; int R = N;

    while (L < R) {
      int mid = (L + R) / 2;
      if (check(mid)) {
        R = mid;
      } else {
        L = mid + 1;
      }
    }
    System.out.println(L);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}