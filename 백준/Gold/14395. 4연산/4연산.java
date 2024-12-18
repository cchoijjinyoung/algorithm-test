
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 4연산 : 골드4
 */
public class Main {
  static long S, T;
  static char[] ops = {'*', '+', '-', '/'};
  static Set<Long> set = new HashSet<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    S = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());
  }

  static String bfs() {
    set.add(S);
    Queue<Info> q = new LinkedList<>();
    q.add(new Info(S, ""));
    while (!q.isEmpty()) {
      Info info = q.poll();
      long num = info.num;
      for (int i = 0; i < 4; i++) {
        StringBuilder command = new StringBuilder(info.command);
        char op = ops[i];
        long next = 0;
        if (op == '*') {
          next = num * num;
        } else if (op == '+') {
          next = num + num;
        } else if (op == '/') {
          if (num == 0) continue;
          next = 1;
        }
        command.append(op);
        if (next == T) {
          return command.toString();
        }
        if (set.contains(next)) {
          continue;
        }
        set.add(next);
        q.add(new Info(next, command.toString()));
      }
    }
    return "-1";
  }

  static void pro() {
    if (S == T) {
      System.out.println(0);
      return;
    }
    System.out.println(bfs());
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Info {
  long num;
  String command;

  Info(long num, String command) {
    this.num = num;
    this.command = command;
  }
}