
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 제로 : 실버4
 */
public class Main {
  static int K, sum;
  static Stack<Integer> stack;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    K = Integer.parseInt(st.nextToken());
    stack = new Stack<>();
    for (int i = 0; i < K; i++) {
      int number = Integer.parseInt(br.readLine());
      if (number == 0) {
        stack.pop();
      } else {
        stack.add(number);
      }
    }
  }

  static void pro() {
    while (!stack.isEmpty()) {
      sum += stack.pop();
    }
    System.out.println(sum);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}