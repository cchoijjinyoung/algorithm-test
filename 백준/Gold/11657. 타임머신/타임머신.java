
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 타임머신 : 골드 4
 * 1반 -> 모든 노드(음의 사이클이 있을 시 -1)
 */
public class Main {
  static int N, M;
  static Edge[] edges;
  static long[] answer;
  static StringBuilder sb = new StringBuilder();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    edges = new Edge[M];
    answer = new long[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      edges[i] = new Edge(from, to, w);
    }
  }

  static void bellmanFord() {
    // 모든 엣지 순회
    for (int i = 0; i < N - 1; i++) {
      for (Edge edge : edges) {
        int from = edge.from;
        int to = edge.to;
        int w = edge.w;

        if (answer[from] == Integer.MAX_VALUE) {
          continue;
        }

        if (answer[from] + w < answer[to]) {
          answer[to] = answer[from] + w;
        }
      }
    }

    // 음의 사이클 유무 확인
    for (Edge edge : edges) {
      int from = edge.from;
      int to = edge.to;
      int w = edge.w;

      if (answer[from] == Integer.MAX_VALUE) {
        continue;
      }

      if (answer[from] + w < answer[to]) {
        System.out.println(-1);
        return;
      }
    }

    for (int i = 2; i <= N; i++) {
      if (answer[i] == Integer.MAX_VALUE) {
        sb.append(-1).append('\n');
      } else {
        sb.append(answer[i]).append('\n');
      }
    }
    System.out.println(sb);
  }

  static void pro() {
    Arrays.fill(answer, Integer.MAX_VALUE);
    answer[1] = 0;
    bellmanFord();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Edge {
  int from;
  int to;
  int w;

  Edge(int from, int to, int w) {
    this.from = from;
    this.to = to;
    this.w = w;
  }
}