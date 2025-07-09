
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static PriorityQueue<Integer> pq = new PriorityQueue<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    for (int i = 0; i < N; i++) {
      pq.add(Integer.parseInt(br.readLine()));
    }
  }

  static void pro() {
    if (N == 1) {
      System.out.println(0);
      return;
    }

    int result = 0;
    while (pq.size() > 1) {
      int a = pq.poll();
      int b = pq.poll();
      int sum = a + b;
      result += sum;
      pq.add(sum);
    }
    System.out.println(result);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}