
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static long[][] arr;
  static long sum, cnt;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    arr = new long[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      long X = Long.parseLong(st.nextToken());
      long A = Long.parseLong(st.nextToken());
      arr[i] = new long[]{X, A};
      cnt += arr[i][1];
    }
  }

  static void pro() {
    Arrays.sort(arr, (e1, e2) -> (int) (e1[0] == e2[0] ? e1[1] - e2[1] : e1[0] - e2[0]));
    
    for (int i = 0; i < N; i++) {
      sum += arr[i][1];
      if (sum >= (cnt + 1) / 2) {
        System.out.println(arr[i][0]);
        break;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}