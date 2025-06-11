
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static Set<Integer> set = new HashSet<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      set.add(Integer.parseInt(st.nextToken()));
    }

    M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      if (set.contains(Integer.parseInt(st.nextToken()))) {
        System.out.println(1);
      } else {
        System.out.println(0);
      }
    }
  }

  static void pro() {

  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}