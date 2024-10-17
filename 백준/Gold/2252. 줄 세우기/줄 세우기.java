
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
  static int N, M;
  static int[] indeg;
  static List<List<Integer>> graph = new ArrayList<>();
  static Queue<Integer> D = new LinkedList<>();
  static StringBuilder sb = new StringBuilder();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    indeg = new int[N + 1];

    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i <= M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      graph.get(from).add(to);
      indeg[to]++;
    }
  }

  static void pro() {
    // 가장 앞에 올 수 있는 학생을 큐에 넣어준다.
    for (int student = 1; student <= N; student++) {
      if (indeg[student] == 0) {
        D.add(student);
      }
    }

    while (!D.isEmpty()) {
      int cur = D.poll();
      sb.append(cur).append(" ");
      for (int to : graph.get(cur)) {
        indeg[to]--;
        if (indeg[to] == 0) {
          D.add(to);
        }
      }
    }
    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}