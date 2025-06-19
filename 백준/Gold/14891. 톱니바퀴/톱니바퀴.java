
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int K;
  static boolean[][] wheels = new boolean[4 + 1][8];
  static int[][] query;
  static int R = 2;
  static int L = 6;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 1; i <= 4; i++) {
      String line = br.readLine();
      for (int j = 0; j < 8; j++) {
        if (line.charAt(j) == '1') {
          wheels[i][j] = true;
        }
      }
    }
    K = Integer.parseInt(br.readLine());
    query = new int[K][2];
    StringTokenizer st;
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int wheel = Integer.parseInt(st.nextToken());
      int direct = Integer.parseInt(st.nextToken());
      query[i] = new int[]{wheel, direct};
    }
  }

  static void pro() {
    for (int i = 0; i < query.length; i++) {
      int wheel = query[i][0];
      int direct = query[i][1];
      rotate(wheel, direct);
    }
    int result = 0;
    result += wheels[1][0] ? 1 : 0;
    result += wheels[2][0] ? 2 : 0;
    result += wheels[3][0] ? 4 : 0;
    result += wheels[4][0] ? 8 : 0;
    System.out.println(result);
  }

  static void turn(int wheel, int direct) {
    boolean temp;
    if (direct == 1) {
      temp = wheels[wheel][7];
      for (int i = 7; i >= 1; i--) {
        wheels[wheel][i] = wheels[wheel][i - 1];
      }
      wheels[wheel][0] = temp;
    } else {
      temp = wheels[wheel][0];
      for (int i = 0; i < 7; i++) {
        wheels[wheel][i] = wheels[wheel][i + 1];
      }
      wheels[wheel][7] = temp;
    }
  }

  static void rotate(int cur, int direct) {
    if (cur < 4 && wheels[cur][R] != wheels[cur + 1][L]) {
      propagation(cur, cur + 1, direct * -1);
    }
    if (cur > 1 && wheels[cur][L] != wheels[cur - 1][R]) {
      propagation(cur, cur - 1, direct * -1);
    }
    turn(cur, direct);
  }

  static void propagation(int prev, int cur, int direct) {
    if (prev < cur) {
      if (cur < 4 && wheels[cur][R] != wheels[cur + 1][L]) {
        propagation(cur, cur + 1, direct * -1);
      }
    } else if (prev > cur) {
      if (cur > 1 && wheels[cur][L] != wheels[cur - 1][R]) {
        propagation(cur, cur - 1, direct * -1);
      }
    }
    turn(cur, direct);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}