
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 꿈틀꿈틀 호석 애벌레
 * 아이디어 :
 * dp[i] := i까지 잘 먹어왔을 때, 최대 만족감
 * 목표 : dp[N];
 */
public class Main {
  static int N, K;
  static long[] A;
  static long[] dp;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    A = new long[N + 1];
    dp = new long[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    int right = 1;
    long sum = 0, dpLeftMax = 0;

    for (int left = 1; left <= N; left++) {
      dpLeftMax = Math.max(dpLeftMax, dp[left - 1]);

      while (right <= N && sum < K) {
        sum += A[right];
        right++;
      }

      if (sum >= K) {
        dp[right - 1] = Math.max(dp[right - 1], dpLeftMax + (sum - K));
      } else {
        break;
      }

      sum -= A[left];
    }
    
    long answer = 0;
    for (int i = 1; i <= N; i++) {
      answer = Math.max(answer, dp[i]);
    }
    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}