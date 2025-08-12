
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int G, P;
  static int[] arr;
  static int[] parent;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    G = Integer.parseInt(br.readLine());
    P = Integer.parseInt(br.readLine());
    arr = new int[P];

    for (int i = 0; i < P; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    parent = new int[G + 1];
    for (int i = 1; i <= G; i++) {
      parent[i] = i;
    }
  }

  static void pro() {
    int answer = 0;
    for (int i = 0; i < P; i++) {
      int g = arr[i];
      int emptyGate = find(g);

      if (emptyGate == 0) {
        break;
      }

      answer++;
      union(emptyGate, emptyGate - 1);
    }
    System.out.println(answer);
  }

  static int find(int x) {
    if (x == parent[x]) {
      return x;
    }
    return parent[x] = find(parent[x]);
  }

  static void union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x != y) {
      parent[x] = y;
    }
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}