import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] jinyoung, cards;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    jinyoung = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      jinyoung[i] = Integer.parseInt(st.nextToken());
    }

    M = Integer.parseInt(br.readLine());
    cards = new int[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      cards[i] = Integer.parseInt(st.nextToken());
    }

    solve();
  }

  public static void solve() {
    Map<Integer, Integer> map = new HashMap<>();
    for (int card : jinyoung) {
      map.put(card, map.getOrDefault(card, 0) + 1);
    }

    StringBuilder sb = new StringBuilder();
    for (int card : cards) {
      sb.append(map.getOrDefault(card, 0));
      sb.append(" ");
    }
    System.out.println(sb.toString());
  }
}
