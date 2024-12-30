
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static double x, y, c;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    x = Double.parseDouble(st.nextToken());
    y = Double.parseDouble(st.nextToken());
    c = Double.parseDouble(st.nextToken());
  }

  static void pro() {
    double L = 0, R = Math.min(x, y);
    
    while (R - L >= 0.001) {
      double mid = (L + R) / 2;
      double h1 = Math.sqrt(Math.pow(x, 2) - Math.pow(mid, 2));
      double h2 = Math.sqrt(Math.pow(y, 2) - Math.pow(mid, 2));
      double result = (h1 * h2) / (h1 + h2);
      
      if (result >= c) {
        L = mid;
      } else {
        R = mid;
      }
    }
    System.out.printf("%.3f%n", R);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}