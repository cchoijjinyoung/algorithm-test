
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static boolean[] visited = new boolean[100001];
  static PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
  }

  static void pro() {
    if (N > K) {
      System.out.println(N - K);
      return;
    }
    bfs();
  }

  static void bfs() {
    pq.add(new int[]{N, 0});

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int pos = cur[0];
      int time = cur[1];

      if (visited[pos]) continue;   // 이미 방문한 경우는 스킵
      visited[pos] = true;

      if (pos == K) {
        System.out.println(time);
        return;
      }

      if (pos + 1 <= 100000) pq.add(new int[]{pos + 1, time + 1});
      if (pos - 1 >= 0) pq.add(new int[]{pos - 1, time + 1});
      if (pos * 2 <= 100000) pq.add(new int[]{pos * 2, time});
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}