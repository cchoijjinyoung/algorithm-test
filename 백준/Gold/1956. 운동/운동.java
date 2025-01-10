
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int V, E;
  static int[][] arr;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());
    arr = new int[V + 1][V + 1];
    for (int i = 1; i <= V; i++) {
      for (int j = 1; j <= V; j++) {
        if (i == j) continue;
        arr[i][j] = Integer.MAX_VALUE;
      }
    }

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      arr[a][b] = c;
    }
  }

  static void pro() {
    for (int k = 1; k <= V; k++) {
      for (int i = 1; i <= V; i++) {
        for (int j = 1; j <= V; j++) {
          if (arr[i][k] == Integer.MAX_VALUE || arr[k][j] == Integer.MAX_VALUE) {
            continue;
          }
          arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
        }
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 1; i <= V; i++) {
      for (int j = 1; j <= V; j++) {
        if (i == j) {
          continue;
        }
        
        if (arr[i][j] == Integer.MAX_VALUE || arr[j][i] == Integer.MAX_VALUE) {
          continue;
        }
        min = Math.min(min, arr[i][j] + arr[j][i]);
      }
    }
    System.out.println(min == Integer.MAX_VALUE ? -1 : min);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}