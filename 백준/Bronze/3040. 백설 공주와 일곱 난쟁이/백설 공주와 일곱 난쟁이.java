
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int[] hobbit;
  static int total;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    hobbit = new int[9];
    for (int i = 0; i < 9; i++) {
      hobbit[i] = Integer.parseInt(br.readLine());
      total += hobbit[i];
    }
  }

  static void pro() {
    int diff = total - 100;
    int faker1 = 0; int faker2 = 0;
    for (int i = 0; i < 8; i++) {
      for (int j = 1; j < 9; j++) {
        if (i == j) {
          continue;
        }
        int sum = hobbit[i] + hobbit[j];
        if (sum == diff) {
          faker1 = i;
          faker2 = j;
        }
      }
    }

    for (int number = 0; number < 9; number++) {
      if (number == faker1 || number == faker2) {
        continue;
      }
      System.out.println(hobbit[number]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}