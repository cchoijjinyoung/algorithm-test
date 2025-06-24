
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
  static int N;
  static Trie trie;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    trie = new Trie();
    for (int i = 0; i < N; i++) {
      trie.add(br.readLine());
    }
  }

  static void pro() {
    trie.print();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }

  static class Trie {
    TreeMap<String, Trie> child = new TreeMap<>();

    void add(String line) {
      StringTokenizer st = new StringTokenizer(line);
      int K = Integer.parseInt(st.nextToken());

      Trie cur = this;
      for (int i = 0; i < K; i++) {
        String food = st.nextToken();
        cur.child.putIfAbsent(food, new Trie());
        cur = cur.child.get(food);
      }
    }

    void print() {
      for (String node : child.keySet()) {
        dfs(this, node, "");
      }
    }

    private void dfs(Trie cur, String node, String gap) {
      System.out.println(gap + node);
      gap += "--";
      Trie next = cur.child.get(node);
      for (String child : next.child.keySet()) {
        dfs(next, child, gap);
      }
    }
  }
}