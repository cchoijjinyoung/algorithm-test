
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Strahler 순서 : 골드3
 */
public class Main {
  static int T, K, M, P;
  static List<List<Integer>> graph;
  static int[] indeg, sequences;
  static int[][] needs;
  static StringBuilder sb = new StringBuilder();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      K = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      P = Integer.parseInt(st.nextToken());

      // 그래프 초기화
      graph = new ArrayList<>();
      indeg = new int[M + 1];
      needs = new int[M + 1][M + 1];
      sequences = new int[M + 1];
      for (int j = 0; j <= M; j++) {
        graph.add(new ArrayList<>());
      }

      while (P-- > 0) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        graph.get(from).add(to);
        indeg[to]++;
      }

      pro();
    }
    System.out.println(sb);
  }

  static void pro() {
    // 강의 근원
    PriorityQueue<int[]> q = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);
    for (int i = 0; i < indeg.length; i++) {
      if (indeg[i] == 0) {
        sequences[i] = 1;
        q.add(new int[]{i, 1});
      }
    }

    while (!q.isEmpty()) {
      int[] info = q.poll();
      int cur = info[0];
      int cur_sequence = info[1];

      for (int next : graph.get(cur)) {
        indeg[next]--;
        needs[next][cur_sequence]++;

        if (indeg[next] == 0) {
          int next_sequence = needs[next][cur_sequence] >= 2 ? cur_sequence + 1 : cur_sequence;
          sequences[next] = next_sequence;
          q.add(new int[]{next, next_sequence});
        }
      }
    }
    sb.append(K).append(' ').append(sequences[M]).append('\n');
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}