
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 영우는 사기꾼 : 골드3
 */
public class Main {
  static int N, M, K;
  static List<List<Integer>> build;
  static List<List<Integer>> needs;
  static int[] indeg;
  static int[] counts;
  static boolean[] canBuild;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    build = new ArrayList<>();
    needs = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      build.add(new ArrayList<>());
      needs.add(new ArrayList<>());
    }

    indeg = new int[N + 1];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      build.get(from).add(to);
      needs.get(to).add(from);
      indeg[to]++;
    }
    counts = new int[N + 1];
    canBuild = new boolean[N + 1];

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int op = Integer.parseInt(st.nextToken());
      int building = Integer.parseInt(st.nextToken());
      if (!check(op, building)) {
        System.out.println("Lier!");
        return;
      }
    }
    System.out.println("King-God-Emperor");
  }

  static boolean check(int op, int building) {
    // 건설
    if (op == 1) {
      if (indeg[building] == 0) {
        counts[building] += 1;

        if (counts[building] == 1) {
          for (int next : build.get(building)) {
            indeg[next] -= 1;
          }
        }
        return true;
      }
    }

    // 파괴
    else if (op == 2) {
      if (counts[building] > 0) {
        counts[building] -= 1;
        if (counts[building] == 0) {
          for (int next : build.get(building)) {
            indeg[next] += 1;
          }
        }
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}