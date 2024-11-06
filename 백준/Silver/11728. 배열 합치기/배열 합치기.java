
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] result;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    result = new int[N + M];

    int idx = 0;
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      result[idx++] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      result[idx++] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    Arrays.sort(result);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < result.length; i++) {
      sb.append(result[i]).append(' ');
    }

    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}