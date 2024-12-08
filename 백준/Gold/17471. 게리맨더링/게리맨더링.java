
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 게리맨더링 : 골드 3
 */
public class Main {
  static int answer = Integer.MAX_VALUE;
  static int N, totalPeopleCount;
  static int[] peoples;
  static List<List<Integer>> list;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    peoples = new int[N + 1];
    list = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      list.add(new ArrayList<>());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      int peopleCount = Integer.parseInt(st.nextToken());
      peoples[i] = peopleCount;
      totalPeopleCount += peopleCount;
    }

    for (int from = 1; from <= N; from++) {
      st = new StringTokenizer(br.readLine());
      int count = Integer.parseInt(st.nextToken());
      for (int i = 0; i < count; i++) {
        int to = Integer.parseInt(st.nextToken());
        list.get(from).add(to);
      }
    }
  }

  static void divide(int count, int[] seg_info, int select_count, int idx) {
    if (select_count == count) {
      calc(seg_info);
      return;
    }

    for (int i = idx; i <= N; i++) {
      seg_info[i] = 1;
      divide(count, seg_info, select_count + 1, i + 1);
      seg_info[i] = 0;
    }
  }

  static void calc(int[] seg_info) {
    // 두 구역으로 나뉘는 지 체크
    int count = 0;
    boolean[] visited = new boolean[N + 1];
    for (int i = 1; i <= N; i++) {
      if (visited[i]) continue;
      count++;
      visited[i] = true;
      bfs(i, seg_info, visited);
    }

    // 구역은 2개여야한다.
    if (count != 2) {
      return;
    }

    int seg_1_peopleCount = 0;
    int seg_2_peopleCount = 0;
    for (int i = 1; i <= N; i++) {
      if (seg_info[i] == 1) {
        seg_1_peopleCount += peoples[i];
      }
    }
    seg_2_peopleCount = totalPeopleCount - seg_1_peopleCount;
    answer = Math.min(answer, Math.abs(seg_1_peopleCount - seg_2_peopleCount));
  }

  static void bfs(int start, int[] seg_info, boolean[] visited) {
    // start 노드부터 탐색
    // 같은 구역이면 visited 체크
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    while (!q.isEmpty()) {
      int cur = q.poll();
      for (int next : list.get(cur)) {
        if (seg_info[cur] != seg_info[next]) {
          continue;
        }

        if (visited[next]) {
          continue;
        }

        visited[next] = true;
        q.add(next);
      }
    }
  }

  static void pro() {
    // 최대 인구수가 N * 100 -> 1000 이므로 answer 의 자료형은 int
    // 하나의 구는 구역 수를 1개부터 N - 1개까지 갖을 수 있다.
    for (int i = 1; i <= N - 1; i++) {
      divide(i, new int[N + 1], 0, 0);
    }

    System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}