import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int G, P;
  static int[] arr;
  static int count;
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
    // 1234로 만들고, 도킹 시 -1을 바라보게 한다.
    for (int airplane : arr) {
      if (check(airplane) == -1) {
        System.out.println(count);
        return;
      }
    }
    System.out.println(count);
  }

  static int check(int a) {
    if (a == 0) {
      return -1;
    }

    if (a != parent[a]) {
      parent[a] = check(parent[a]);
      return parent[a];
    }
    count++;
    return parent[a] = a - 1;
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}