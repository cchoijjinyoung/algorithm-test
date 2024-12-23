
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, K;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
  }

  static String create(int target, int count) {
    StringBuilder sb = new StringBuilder();
    // count는 A의 갯수
    // count - 1개 만큼 A를 append;
    for (int i = 0; i < count - 1; i++) {
      sb.append('A');
    }

    // B는 K - target만큼 붙여줌
    for (int i = 0; i < target - K; i++) {
      sb.append('B');
    }
    sb.append('A');

    int remain = N - sb.length();
    for (int i = 0; i < remain; i++) {
      sb.append('B');
    }
    return sb.toString();
  }

  static void pro() {
    int max = (N / 2) * (N - N / 2);
    if (K > max) {
      System.out.println(-1);
      return;
    }
    int i = 0;
    while (i++ <= N) {
      int target = (N - i) * i;
      if (target >= K) {
        System.out.println(create(target, i));
        return;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}