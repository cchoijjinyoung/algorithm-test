
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * ! 아이디어
 * 간선정보(부모 -> 자식)를 보고 각 노드별 부모 노드 저장(parents[N + 1])
 * Queue 에 공통 부모를 찾을 두 노드를 넣고, 시작
 * 위로 올라가면서 각 부모를 set 에 담음
 * set 에 이미 존재하는 부모일 시, 최소 공통 부모이다.
 */
public class Main {
  static int T, N;
  static int node1, node2;
  static int[] parents;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      N = Integer.parseInt(br.readLine());
      parents = new int[N + 1];

      for (int j = 0; j < N - 1; j++) {
        st = new StringTokenizer(br.readLine());
        int parent = Integer.parseInt(st.nextToken());
        int child = Integer.parseInt(st.nextToken());
        parents[child] = parent;
      }

      st = new StringTokenizer(br.readLine());
      node1 = Integer.parseInt(st.nextToken());
      node2 = Integer.parseInt(st.nextToken());
      pro();
    }
  }

  static void pro() {
    Set<Integer> set = new HashSet<>();
    Queue<Integer> q = new LinkedList<>();
    q.add(node1);
    q.add(node2);
    set.add(node1);
    set.add(node2);

    while (!q.isEmpty()) {
      int cur = q.poll();

      // 현재 노드가 루트 노드면 continue;
      if (parents[cur] == 0) {
        continue;
      }

      int parent = parents[cur];
      if (set.contains(parent)) {
        System.out.println(parent);
        return;
      }
      set.add(parent);
      q.add(parent);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}