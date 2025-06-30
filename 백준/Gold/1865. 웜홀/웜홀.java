import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int TC, N, M, W;
  static List<int[]> edges;
  static int[] dist;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    TC = Integer.parseInt(st.nextToken());
    for (int i = 0; i < TC; i++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());

      edges = new ArrayList<>();

      dist = new int[N + 1];

      for (int j = 0; j < M; j++) {
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        edges.add(new int[]{S, E, T});
        edges.add(new int[]{E, S, T});
      }

      for (int j = 0; j < W; j++) {
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        edges.add(new int[]{S, E, -T});
      }

      for (int k = 1; k <= N; k++) {
        edges.add(new int[]{0, k, 0});
      }
      
      pro();
    }
  }

  static void pro() {
    if (bmfd(0)) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
    }
  }

  static boolean bmfd(int start) {
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;

    for (int i = 0; i < N; i++) {
      for (int[] edge : edges) {
        int from = edge[0];
        int to = edge[1];
        int w = edge[2];
        if (dist[from] != Integer.MAX_VALUE && dist[to] > dist[from] + w) {
          dist[to] = dist[from] + w;
        }
      }
    }

    for (int[] edge : edges) {
      int from = edge[0];
      int to = edge[1];
      int w = edge[2];
      if (dist[from] != Integer.MAX_VALUE && dist[to] > dist[from] + w) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}