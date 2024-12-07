
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int answer = Integer.MAX_VALUE;
  static int[] B;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    B = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void dfs(int gap, int[] result, int count, int depth) {
    if (depth == N) {
      answer = Math.min(answer, count);
      return;
    }
    result[depth] = B[depth] + gap;

    if (depth != 0) {
      if (result[1] - result[0] != result[depth] - result[depth - 1]) {
        return;
      }
    }

    for (int i : new int[]{-1, 0, 1}) {
      if (i != 0) {
        dfs(i, result, count + 1, depth + 1);
      } else {
        dfs(i, result, count, depth + 1);
      }
    }
  }

  static void pro() {
    if (N == 1) {
      System.out.println(0);
      return;
    }
    for (int i : new int[]{-1, 0, 1}) {
      int[] arr = new int[N];
      int count = 0;
      if (i != 0) count++;
      dfs(i, arr, count, 0);
    }

    System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}