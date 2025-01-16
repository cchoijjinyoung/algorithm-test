
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  static String str, target;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    str = br.readLine();
    target = br.readLine();
  }

  static void pro() {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < str.length(); i++) {
      stack.push(str.charAt(i));

      if (stack.size() >= target.length()) {
        boolean flag = true;
        for (int j = 0; j < target.length(); j++) {
          if (stack.get(stack.size() - target.length() + j) != target.charAt(j)) {
            flag = false;
            break;
          }
        }
        if (flag) {
          for (int j = 0; j < target.length(); j++) {
            stack.pop();
          }
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (Character c : stack) {
      sb.append(c);
    }
    System.out.println(sb.isEmpty() ? "FRULA" : sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}