
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 수 묶기 : 골드 4
 */
public class Main {
  static int N;
  static PriorityQueue<Integer> plus;
  static PriorityQueue<Integer> minus;
  static Queue<Integer> zero;
  static int answer = 0;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    plus = new PriorityQueue<>((e1, e2) -> e2 - e1);
    minus = new PriorityQueue<>();
    zero = new LinkedList<>();

    for (int i = 0; i < N; i++) {
      int number = Integer.parseInt(br.readLine());
      if (number > 0) {
        plus.add(number);
      } else if (number < 0) {
        minus.add(number);
      } else {
        zero.add(number);
      }
    }
  }

  static void pro() {
    while (plus.size() >= 2) {
      int number1 = plus.poll();
      int number2 = plus.poll();

      if (number1 == 1 || number2 == 1) {
        answer += number1;
        answer += number2;
        continue;
      }
      answer += number1 * number2;
    }

    while (minus.size() >= 2) {
      int number1 = minus.poll();
      int number2 = minus.poll();
      answer += number1 * number2;
    }

    if (minus.size() == 1 && zero.size() > 0) {
      answer += minus.poll() * zero.poll();
    }

    while (!plus.isEmpty()) {
      answer += plus.poll();
    }

    while (!minus.isEmpty()) {
      answer += minus.poll();
    }

    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}