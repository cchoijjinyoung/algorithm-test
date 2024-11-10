import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K, P, X;
  static int[][] LED;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());

    LED = new int[10][7];
    LED[0] = new int[]{1, 1, 1, 0, 1, 1, 1};
    LED[1] = new int[]{0, 0, 1, 0, 0, 1, 0};
    LED[2] = new int[]{1, 0, 1, 1, 1, 0, 1};
    LED[3] = new int[]{1, 0, 1, 1, 0, 1, 1};
    LED[4] = new int[]{0, 1, 1, 1, 0, 1, 0};
    LED[5] = new int[]{1, 1, 0, 1, 0, 1, 1};
    LED[6] = new int[]{1, 1, 0, 1, 1, 1, 1};
    LED[7] = new int[]{1, 0, 1, 0, 0, 1, 0};
    LED[8] = new int[]{1, 1, 1, 1, 1, 1, 1};
    LED[9] = new int[]{1, 1, 1, 1, 0, 1, 1};
  }

  static int diffCount(int x, int y) {
    int result = 0;
    for (int i = 0; i < 7; i++) {
      if (LED[x][i] != LED[y][i]) result++;
    }
    return result;
  }

  static int diff(int x, int y) {
    int result = 0;
    for (int i = 1; i <= K; i++) {
      result += diffCount(x % 10, y % 10);
      x /= 10;
      y /= 10;
    }
    return result;
  }

  static void pro() {
    int answer = 0;
    for (int target = 1; target <= N; target++) {
      if (X == target) continue;
      if (diff(X, target) <= P) answer++;
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}