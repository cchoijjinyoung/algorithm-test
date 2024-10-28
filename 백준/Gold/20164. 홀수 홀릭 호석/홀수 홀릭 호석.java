
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 홀수 홀릭 호석, 골드5
 */
public class Main {
  static int min, max;
  static String N;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = br.readLine();
  }

  static void cut(int count, String num) {
    for (char c : num.toCharArray()) {
      if ((c - '0') % 2 == 1) {
        count++;
      }
    }

    if (num.length() == 1) {
      min = Math.min(min, count);
      max = Math.max(max, count);
      return;
    }
    if (num.length() >= 3) {
      for (int lt = 1; lt <= num.length() - 2; lt++) {
        for (int rt = lt + 1; rt <= num.length() - 1; rt++) {
          int front = Integer.parseInt(num.substring(0, lt));
          int middle = Integer.parseInt(num.substring(lt, rt));
          int back = Integer.parseInt(num.substring(rt));
          cut(count, String.valueOf(front + middle + back));
        }
      }
    } else if (num.length() == 2) {
      int front = num.charAt(0) - '0';
      int back = num.charAt(1) - '0';
      cut(count, String.valueOf(front + back));
    }
  }

  static void pro() {
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;
    cut(0, N);
    System.out.println(min + " " + max);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}