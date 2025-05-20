import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int n, k;
  static int[] arr;
  static int[] parent;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    while (true) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());

      if (n == 0 && k == 0) break;

      arr = new int[n + 1];
      arr[0] = -1;
      parent = new int[n + 1];
      parent[0] = -1;
      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= n; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      solve();
    }
  }

  public static void solve() {
    int target = 0;
    int idx = -1;

    for (int i = 1; i <= n; i++) {
      if (arr[i] == k) target = i;
      if (arr[i] != arr[i - 1] + 1) idx++;
      parent[i] = idx;
    }

    int answer = 0;
    for (int i = 1; i <= n; i++) {
      if (parent[i] != parent[target] && parent[parent[i]] == parent[parent[target]]) {
        answer++;
      }
    }
    System.out.println(answer);
  }
}
