
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] trueMan;
  static Set<Integer> trueRoots = new HashSet<>();
  static List<List<Integer>> partyList = new ArrayList<>();
  static int[] parent;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 사람의 수, 파티의 수
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    // 진실을 아는 사람의 수, 그 개수만큼의 사람 번호
    st = new StringTokenizer(br.readLine());
    int k = Integer.parseInt(st.nextToken());
    trueMan = new int[k];
    for (int i = 0; i < k; i++) {
      trueMan[i] = Integer.parseInt(st.nextToken());
    }

    // M개의 줄에 각 파티마다 오는 사람의 수, 그 개수만큼의 사람 번호
    for (int i = 0; i < M; i++) {
      partyList.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());
      for (int j = 0; j < size; j++) {
        partyList.get(i).add(Integer.parseInt(st.nextToken()));
      }
    }

    // parent 배열 초기화
    parent = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }
  }

  static void pro() {
    int result = 0;

    // 유니온-파인드
    for (int i = 0; i < M; i++) {
      // 파티마다 첫번째 사람을 리더로
      List<Integer> party = partyList.get(i);
      int leader = party.get(0);

      // 나머지 파티원들을 리더와 같은 그룹으로 union
      for (int j = 1; j < party.size(); j++) {
        union(leader, party.get(j));
      }
    }

    for (int man : trueMan) {
      trueRoots.add(find(man));
    }

    for (int i = 0; i < M; i++) {
      List<Integer> party = partyList.get(i);
      int leader = party.get(0);

      if (trueRoots.contains(find(leader))) {
        continue;
      }
      result++;
    }

    System.out.println(result);
  }

  static void union(int a, int b) {
    a = find(a);
    b = find(b);
    if (a != b) {
      parent[a] = b;
    }
  }

  static int find(int a) {
    if (parent[a] == a) {
      return a;
    }
    return parent[a] = find(parent[a]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}