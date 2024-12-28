
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 치킨 배달 : 골드5
 * 브루트포스로 하면 시간복잡도가 어떻게 되는가?
 * 2M * 13 * 13C6 인가?
 */
public class Main {
  static int N, M;
  static List<int[]> chickens;
  static List<int[]> homes;
  static int answer = Integer.MAX_VALUE;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    chickens = new ArrayList<>();
    homes = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int number = Integer.parseInt(st.nextToken());
        if (number == 1) {
          homes.add(new int[]{i, j});
        }
        if (number == 2) {
          chickens.add(new int[]{i, j});
        }
      }
    }
  }

  static void recursive(int[] selected, int depth, int idx) {
    if (depth == M) {
      int result = calc(selected);
      answer = Math.min(answer, result);
      return;
    }

    for (int i = idx; i < chickens.size(); i++) {
      selected[depth] = i;
      recursive(selected, depth + 1, i + 1);
    }
  }

  static int calc(int[] selected) {
    int sum = 0;
    // 각 집마다 가장 가까운 치킨집을 고르고, sum 에 더한다.
    // 시간복잡도 = 2N * 13
    for (int[] home : homes) {
      int min = Integer.MAX_VALUE;
      for (int index : selected) {
        int[] chicken = chickens.get(index);
        int dist = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
        min = Math.min(min, dist);
      }
      sum += min;
    }
    return sum;
  }

  static void pro() {
    // 재귀로 모든 경우의 수 탐색
    // m 크기의 배열 생성
    recursive(new int[M], 0, 0);
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}