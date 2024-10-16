
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
  static int N;
  static int K;
  static int answer;
  static boolean[] visited;
  static int[] dx = {-1, 1, 0};

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    visited = new boolean[200001];
    answer = Math.abs(N - K);
  }

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();
    q.add(N); // 현재 위치
    q.add(0); // 초
    visited[N] = true;

    while (!q.isEmpty()) {
      int cur = q.poll();
      int second = q.poll();

      for (int i = 0; i < 3; i++) {
        int next = dx[i] == 0 ? cur * 2 : cur + dx[i];

        if (next < 0 || next > 200000) {
          continue;
        }

        if (visited[next]) {
          continue;
        }

        if (next == K) {
          answer = second + 1;
          return;
        }

        visited[next] = true;
        q.add(next);
        q.add(second + 1);
      }
    }
  }

  static void pro() {
    bfs();
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}