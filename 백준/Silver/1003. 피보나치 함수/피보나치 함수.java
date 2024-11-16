
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 피보나치 함수
 */
public class Main {
  static int T;
  static List<Integer> cases;
  static int[][] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    cases = new ArrayList<>();

    while (br.ready()) {
      String line = br.readLine();

      int number = Integer.parseInt(line);
      cases.add(number);
    }

    dp = new int[40 + 1][2];
  }

  static void pro() {
    dp[0][0] = 1; // 피보나치(0)의 0 출력 횟수
    dp[0][1] = 0; // 피보나치(0)의 1 출력 횟수
    dp[1][0] = 0;
    dp[1][1] = 1;

    for (int i = 2; i <= 40; i++) {
      dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
      dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
    }

    for (int i = 0; i < T; i++) {
      int number = cases.get(i);
      System.out.println(dp[number][0] + " " + dp[number][1]);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();

  }
}