
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 물통 : 골드4
 * 알고리즘 : 그래프 탐색
 */
class Main {
  static int[] water = new int[3];
  static int[] bottle = new int[3];
  static Set<Integer> set = new HashSet<>();
  static List<Integer> result = new ArrayList<>();
  static boolean[][][] visited;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < 3; i++) {
      water[i] = Integer.parseInt(st.nextToken());
    }

    visited = new boolean[water[0] + 1][water[1] + 1][water[2] + 1];
  }

  static void dfs(int r, int s) {
    if (bottle[r] == 0) {
      return;
    }

    if (bottle[s] == water[s]) {
      return;
    }

    int amount = Math.min(bottle[r], water[s] - bottle[s]);
    bottle[r] -= amount;
    bottle[s] += amount;

    if (bottle[0] == 0) {
      set.add(bottle[2]);
    }

    if (visited[bottle[0]][bottle[1]][bottle[2]]) {
      bottle[r] += amount;
      bottle[s] -= amount;
      return;
    }
    visited[bottle[0]][bottle[1]][bottle[2]] = true;

    dfs(0, 1);
    dfs(0, 2);

    dfs(1, 0);
    dfs(1, 2);

    dfs(2, 0);
    dfs(2, 1);

    bottle[r] += amount;
    bottle[s] -= amount;
  }

  static void pro() {
    bottle[2] = water[2];
    visited[0][0][bottle[2]] = true;
    set.add(bottle[2]);

    dfs(2, 0); // 2 -> 0 : C -> A
    dfs(2, 1); // C -> B

    result.addAll(set);
    Collections.sort(result);

    for (int i : result) {
      System.out.print(i + " ");
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}