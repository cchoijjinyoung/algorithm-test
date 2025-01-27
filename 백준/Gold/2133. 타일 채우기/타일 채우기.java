import java.io.*;
import java.util.*;

public class Main {
  public static int N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    input(br);
    pro();
  }

  static void input(BufferedReader br) throws Exception {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
  }

  static void pro() {
    int[] DP = new int[N + 1];


    if (N % 2 != 0) {
      System.out.println(0);
      return;
    }

    DP[0] = 1;
    DP[1] = 0;
    DP[2] = 3;

    for (int i = 4; i <= N; i += 2) {
      DP[i] = DP[i - 2] * 3;
      // 4, 6, 8, ...
      for (int j = i - 4; j >= 0; j -= 2) {
        DP[i] += DP[j] * 2;
      }
    }

    // 결과값 출력
    System.out.println(DP[N]);
  }
}