import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] arr;
  static StringBuilder sb = new StringBuilder();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new int[M];
  }
  
  static void dfs(int idx, int depth) {
    if (depth == M) {
      print();
      return;
    }
    
    for (int i = idx; i <= N; i++) {
      arr[depth] = i;
      dfs(i + 1, depth + 1);
    }
  }
  
  static void print() {
    for (int number : arr) {
      sb.append(number).append(' ');
    }
    sb.append('\n');
  }

  static void pro() {
    dfs(1, 0);
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}