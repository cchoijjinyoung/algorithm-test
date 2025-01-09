
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int T;
  static int N;
  static int[] select;
  static int count;
  static boolean[] done;
  static boolean[] visited;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      N = Integer.parseInt(br.readLine());
      select = new int[N + 1];
      done = new boolean[N + 1];
      visited = new boolean[N + 1];
      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++) {
        int s = Integer.parseInt(st.nextToken());
        select[i] = s;
      }
      pro();
    }
  }

  static void dfs(int cur) {
    if (done[cur]) {
      return;
    }
    if (visited[cur]) {
      done[cur] = true;
      count++;
    }
    visited[cur] = true;
    dfs(select[cur]);
    done[cur] = true;
    visited[cur] = false;
  }

  static void pro() {
    count = 0;
    for (int i = 1; i <= N; i++) {
      if (done[i]) {
        continue;
      }
      dfs(i);
    }
    System.out.println(N - count);
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}