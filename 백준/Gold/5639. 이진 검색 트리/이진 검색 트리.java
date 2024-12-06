import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static Node root;

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int rootValue = Integer.parseInt(br.readLine());
    root = new Node(rootValue);

    String line;
    while ((line = br.readLine()) != null) {
      int value = Integer.parseInt(line);
      root.add(new Node(value));
    }
  }

  static void pro() {
    root.lrc();
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}

class Node {
  int value;
  Node L;
  Node R;

  Node(int value) {
    this.value = value;
  }

  void add(Node node) {
    if (value > node.value) {
      if (L == null) {
        L = node;
      } else {
        L.add(node);
      }
    } else {
      if (R == null) {
        R = node;
      } else {
        R.add(node);
      }
    }
  }

  void lrc() {
    if (L != null) {
      L.lrc();
    }

    if (R != null) {
      R.lrc();
    }
    System.out.println(value);
  }
}