
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int T, N;
  static String[] calls;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      N = Integer.parseInt(br.readLine());
      calls = new String[N];
      for (int j = 0; j < N; j++) {
        calls[j] = br.readLine();
      }
      Arrays.sort(calls, (c1, c2) -> c1.length() - c2.length());
      pro();
    }
  }

  static void pro() {
    Set<String> set = new HashSet<>();
    for (String call : calls) {
      for (int i = 1; i < call.length(); i++) {
        String sub = call.substring(0, i);
        if (set.contains(sub)) {
          System.out.println("NO");
          return;
        }
      }
      set.add(call);
    }
    System.out.println("YES");
  }

  public static void main(String[] args) throws Exception {
    input();
  }
}