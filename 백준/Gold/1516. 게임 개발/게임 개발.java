
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] times;
  static int[] indeg;
  static int[] answer;
  static List<List<Integer>> graph;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    times = new int[N + 1];
    indeg = new int[N + 1];
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int time = Integer.parseInt(st.nextToken());
      times[i] = time;

      while (true) {
        int need = Integer.parseInt(st.nextToken());
        if (need == -1) {
          break;
        }
        graph.get(need).add(i);
        indeg[i]++;
      }
    }
  }

  static void pro() {
    answer = new int[N + 1];
    PriorityQueue<int[]> q = new PriorityQueue<>((b1, b2) -> b1[1] - b2[1]);
    // indeg 가 0인 건물을 큐에 넣는다.
    // 건물을 짓고 난 후, 새롭게 지을 수 있는 건물(indeg가 새롭게 0으로 된)을 큐에 넣는다.
    for (int i = 1; i <= N; i++) {
      if (indeg[i] == 0) {
        q.add(new int[]{i, times[i]});
      }
    }

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cur = info[0];
      int cur_time = info[1];
      answer[cur] = cur_time;

      for (int next : graph.get(cur)) {
        indeg[next]--;

        if (indeg[next] == 0) {
          q.add(new int[]{next, cur_time + times[next]});
        }
      }
    }
    for (int i = 1; i <= N; i++) {
      System.out.println(answer[i]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}