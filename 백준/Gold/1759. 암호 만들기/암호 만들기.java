import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int L, C;
  static char[] A;
  static boolean[] visited;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    A = new char[C];
    visited = new boolean[C];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < C; i++) {
      A[i] = st.nextToken().charAt(0);
    }
  }

  static void dfs(char[] arr, int depth, int idx, int z, int m) {
    if (depth == L) {
      if (z >= 2 && m >= 1) {
        System.out.println(String.valueOf(arr));
      }
      return;
    }

    for (int i = idx; i < A.length; i++) {
      if (visited[i]) {
        continue;
      }
      visited[i] = true;
      arr[depth] = A[i];

      if (isMoem(A[i])) {
        m++;
      } else {
        z++;
      }
      dfs(arr, depth + 1, i + 1, z, m);
      visited[i] = false;
      if (isMoem(A[i])) {
        m--;
      } else {
        z--;
      }
    }
  }

  static boolean isMoem(char c) {
    if (c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u') {
      return true;
    }
    return false;
  }

  static void pro() {
    Arrays.sort(A);
    char[] arr = new char[L];
    dfs(arr, 0, 0, 0, 0);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}