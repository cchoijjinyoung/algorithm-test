import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 파일정리, 실버3
 * 알고리즘: 정렬
 */
class Main {
  static int N;
  static String[] A;
  static StringBuilder sb = new StringBuilder();

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    A = new String[N + 1];

    for (int i = 1; i <= N; i++) {
      String s = br.readLine();
      String x = s.split("\\.")[1];
      A[i] = x;
    }
  }

  public static void main(String[] args) throws Exception {
    input();

    Arrays.sort(A, 1, N + 1);

    String answer = A[1];
    int curCnt = 1;

    for (int i = 2; i <= N; i++) {
      if (A[i].equals(A[i - 1])) {
        curCnt++;
      } else {
        sb.append(A[i - 1]).append(' ').append(String.valueOf(curCnt)).append('\n');
        curCnt = 1;
      }
    }
    sb.append(A[N]).append(' ').append(String.valueOf(curCnt)).append('\n');
    System.out.println(sb.toString());
  }
}