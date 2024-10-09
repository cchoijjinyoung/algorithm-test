import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
  static int N;
  static int[] P;
  static Elem[] B;
  static StringBuilder sb = new StringBuilder();

  static class Elem implements Comparable<Elem> {
    public int idx, num;

    @Override
    public int compareTo(Elem o) {
      if (num != o.num) return num - o.num;
      return idx - o.idx;
    }
  }

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    P = new int[N];
    B = new Elem[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      B[i] = new Elem();
      B[i].num = Integer.parseInt(st.nextToken());
      B[i].idx = i;
    }
  }

  public static void main(String[] args) throws Exception {
    input();

    Arrays.sort(B);

    for (int i = 0; i < B.length; i++) {
      P[B[i].idx] = i;
    }

    for (int j : P) {
      sb.append(j).append(' ');
    }

    System.out.println(sb.toString());
  }
}