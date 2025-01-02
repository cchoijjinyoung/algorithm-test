
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] times;
  static int[] values;
  static int[] dp;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    times = new int[N + 2];
    values = new int[N + 2];

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int time = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      times[i] = time;
      values[i] = value;
    }

    dp = new int[N + 2];
  }

  static void pro() {
    int max = 0;
    for (int day = 1; day <= N + 1; day++) {
      max = Math.max(max, dp[day]);

      int time = times[day];
      int value = values[day];
      if (day + time > N + 1) {
        continue;
      }
      dp[day + time] = Math.max(dp[day + time], max + value);
    }
    System.out.println(max);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}