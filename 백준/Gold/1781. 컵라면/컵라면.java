
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static PriorityQueue<Integer> pq;
  static int[][] arr;
  static long answer;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    arr = new int[N + 1][2];
    StringTokenizer st;
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int deadLine = Integer.parseInt(st.nextToken());
      int cup = Integer.parseInt(st.nextToken());
      arr[i] = new int[]{deadLine, cup};
    }
  }

  static void pro() {
    Arrays.sort(arr, (q1, q2) -> q1[0] - q2[0]);
    pq = new PriorityQueue<>();

    for (int i = 1; i <= N; i++) {
      int deadLine = arr[i][0];
      int cup = arr[i][1];

      pq.add(cup);
      
      if (pq.size() > deadLine) {
        pq.poll();
      }
    }

    while (!pq.isEmpty()) {
      answer += pq.poll();
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}