
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
 * 장난감 조립 : 골드2
 */
public class Main {
  static int N, M;
  static int[] indeg;
  static int[][] needs;
  static List<List<int[]>> legos;
  static List<Integer> baseParts;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());
    indeg = new int[N + 1];
    needs = new int[N + 1][N + 1];
    legos = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      legos.add(new ArrayList<>());
    }
    baseParts = new ArrayList<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int X = Integer.parseInt(st.nextToken());
      int Y = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      legos.get(Y).add(new int[]{X, K});
      indeg[X]++;
    }
  }

  static void pro() {
    // 기본 부품을 찾는다.
    for (int part = 1; part <= N; part++) {
      if (indeg[part] == 0) {
        baseParts.add(part);
        needs[part][part] = 1;
      }
    }

    // 중간 부품들이 몇개의 기본 부품이 필요한지 탐색
    Queue<Integer> q = new LinkedList<>(baseParts);
    while (!q.isEmpty()) {
      int cur = q.poll();

      // 현재 부품으로 만들 수 있는 중간 부품을 순회
      for (int[] info : legos.get(cur)) {
        int next = info[0];
        int count = info[1];

        // 현재부품에서 필요한 기본부품 수량 만큼, 중간부품의 기본부품 수량에 추가
        for (int basePart : baseParts) {
          needs[next][basePart] += needs[cur][basePart] * count;
        }

        indeg[next]--;
        if (indeg[next] == 0) {
          q.add(next);
        }
      }
    }

    // 필요한 기본부품 수 출력
    for (int basePart : baseParts) {
      System.out.println(basePart + " " + needs[N][basePart]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Part {
  int number;
  int count;

  Part(int number, int count) {
    this.number = number;
    this.count = count;
  }
}