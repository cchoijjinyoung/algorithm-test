
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] P;
  static int[] S;
  static int[] cards;
  static int[] next;
  static Set<String> visited = new HashSet<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    P = new int[N];
    S = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      P[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      S[i] = Integer.parseInt(st.nextToken());
    }

    cards = new int[N];
    for (int i = 0; i < N; i++) {
      cards[i] = i;
    }
  }

  static void pro() {
    int step = 0;

    while (true) {
      boolean ok = true;
      for (int i = 0; i < N; i++) {
        if (cards[i] % 3 != P[i]) {
          ok = false;
          break;
        }
      }

      if (ok) {
        System.out.println(step);
        return;
      }

      String key = Arrays.toString(cards);
      if (visited.contains(key)) {
        System.out.println(-1);
        return;
      }
      visited.add(key);

      next = new int[N];
      for (int i = 0; i < N; i++) {
        next[i] = S[cards[i]];
      }
      cards = next;
      step++;
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}