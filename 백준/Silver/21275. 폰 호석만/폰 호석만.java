import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static String x1, x2;
  static int successCount;
  static long X;
  static int A, B;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    x1 = st.nextToken(); // X를 A진법으로 표현
    x2 = st.nextToken(); // X를 B진법으로 표현

    solve();
  }

  public static void solve() {
    for (int a = 2; a <= 36; a++) {
      long x = calculate(x1, a);
      if (!validate(x)) continue;
      for (int b = 2; b <= 36; b++) {
        long compare = calculate(x2, b);
        if (!validate(compare)) continue;
        if (a != b && x == compare) {
          X = x;
          A = a;
          B = b;
          successCount++;
        }
      }
    }
    if (successCount == 1) {
      System.out.println(X + " " + A + " " + B);
    } else if (successCount >= 2) {
      System.out.println("Multiple");
    } else if (successCount == 0) {
      System.out.println("Impossible");
    }
  }

  public static long calculate(String target, int a) {
    long result = 0;
    int len = target.length();

    // 999a
    for (int i = 0; i < len; i++) {
      char c = target.charAt(i);
      int number = convertingInt(c);
      if (number >= a) return -1;
      result += (long)Math.pow((long)a, len - i - 1) * (long)number;
    }
    return result;
  }

  public static int convertingInt(char c) {
    if (c <= '9') return c - '0';
    return c - 'a' + 10;
  }

  public static boolean validate(long target) {
    if (target == -1) return false;
    return 0 <= target;
  }
}
