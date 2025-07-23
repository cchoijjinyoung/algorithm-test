
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static long[][] points;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    points = new long[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      points[i] = new long[]{x, y};
    }
  }

  static void pro() {
    double area = 0;

    for (int i = 1; i < N - 1; i++) {
      long[] A = points[0];
      long[] B = points[i];
      long[] C = points[i + 1];
      area += triangleArea(A, B, C);
    }

    System.out.printf("%.1f", Math.abs(area) / 2.0);
  }

  // 외적을 이용한 삼각형 넓이
  static double triangleArea(long[] a, long[] b, long[] c) {
    return (b[0] - a[0]) * (c[1] - a[1]) - (c[0] - a[0]) * (b[1] - a[1]);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}