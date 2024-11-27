
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
  static int[] times, indeg;
  static List<List<Integer>> tasks;
  static PriorityQueue<int[]> q = new PriorityQueue<>((t1, t2) -> t1[1] - t2[1]);
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    tasks = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      tasks.add(new ArrayList<>());
    }

    times = new int[N + 1];
    indeg = new int[N + 1];

    for (int task = 1; task <= N; task++) {
      st = new StringTokenizer(br.readLine());
      int time = Integer.parseInt(st.nextToken());
      int count = Integer.parseInt(st.nextToken());
      times[task] = time;

      while (count-- > 0) {
        int need = Integer.parseInt(st.nextToken());
        tasks.get(task).add(need);
        indeg[need]++;
      }
    }
  }

  static void pro() {
    int answer = 0;
    for (int task = 1; task <= N; task++) {
      if (indeg[task] == 0) {
        q.add(new int[]{task, times[task]});
      }
    }

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cur = info[0];
      int curTime = info[1];
      answer = Math.max(answer, curTime);
      
      for (int next : tasks.get(cur)) {
        indeg[next]--;
        if (indeg[next] == 0) {
          int endTime = curTime + times[next];
          q.add(new int[]{next, endTime});
        }
      }
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}