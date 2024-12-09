
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N, answer;
  static int[] W;
  static int[] S;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    W = new int[N];
    S = new int[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      S[i] = s;
      W[i] = w;
    }
  }

  static void dfs(int cur, int breakCount) {
    if (breakCount == N - 1 || cur == N) {
      answer = Math.max(answer, breakCount);
      return;
    }

    if (S[cur] <= 0) {
      dfs(cur + 1, breakCount);
      return;
    }
    for (int target = 0; target < N; target++) {
      if (target == cur) {
        continue;
      }

      if (S[target] > 0) {
        S[cur] -= W[target];
        S[target] -= W[cur];

        int newBreakCount = 0;
        if (S[cur] <= 0) {
          newBreakCount++;
        }

        if (S[target] <= 0) {
          newBreakCount++;
        }

        dfs(cur + 1, breakCount + newBreakCount);
        S[cur] += W[target];
        S[target] += W[cur];
      }
    }

  }

  static void pro() {
    dfs(0, 0);
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}