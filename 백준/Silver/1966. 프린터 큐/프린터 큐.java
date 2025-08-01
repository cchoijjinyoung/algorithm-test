
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int T;
  static PriorityQueue<Integer> pq;
  static Queue<int[]> q;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    while (T-- > 0) {
      pq = new PriorityQueue<>((e1, e2) -> e2 - e1);
      q = new LinkedList<>();
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        int num = Integer.parseInt(st.nextToken());
        pq.add(num);
        q.add(new int[]{i, num});
      }
      pro(M);
    }
  }

  static void pro(int M) {
    int count = 1;
    while (true) {
      int[] cur = q.peek();
      int value = pq.peek();
      int seq = cur[0];
      int originalValue = cur[1];

      if (originalValue != value) {
        q.poll();
        q.add(cur);
      } else {
        pq.poll();
        q.poll();
        if (seq == M) {
          System.out.println(count);
          break;
        }
        count++;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}