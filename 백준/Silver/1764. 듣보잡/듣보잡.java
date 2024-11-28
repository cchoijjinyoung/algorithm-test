
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
  static int N, M, count;
  static Set<String> set;
  static List<String> deutbojop;
  static StringBuilder sb;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    set = new HashSet<>();
    deutbojop = new ArrayList<>();
    sb = new StringBuilder();

    for (int i = 0; i < N + M; i++) {
      String name = br.readLine();
      if (set.contains(name)) {
        count++;
        deutbojop.add(name);
      }
      set.add(name);
    }
  }

  static void pro() {
    System.out.println(count);
    Collections.sort(deutbojop);
    for (String name : deutbojop) {
      System.out.println(name);
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}