
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static PriorityQueue<int[]> A, B;
  static List<Integer> result = new ArrayList<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    A = new PriorityQueue<>((e1, e2) -> e1[0] == e2[0] ? e1[1] - e2[1] : e2[0] - e1[0]);
    for (int i = 0; i < N; i++) {
      int element = Integer.parseInt(st.nextToken());
      A.add(new int[]{element, i});
    }

    M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    B = new PriorityQueue<>((e1, e2) -> e1[0] == e2[0] ? e1[1] - e2[1] : e2[0] - e1[0]);
    for (int i = 0; i < M; i++) {
      int element = Integer.parseInt(st.nextToken());
      B.add(new int[]{element, i});
    }
  }

  static void pro() {
    int ai = 0;
    int bi = 0;
    while (true) {
      while (!A.isEmpty()) {
        int index = A.peek()[1];
        if (index < ai) {
          A.poll();
        } else {
          break;
        }
      }

      while (!B.isEmpty()) {
        int index = B.peek()[1];
        if (index < bi) {
          B.poll();
        } else {
          break;
        }
      }

      if (A.isEmpty() || B.isEmpty()) {
        break;
      }

      int[] aInfo = A.peek();
      int[] bInfo = B.peek();
      int a = aInfo[0];
      int b = bInfo[0];

      int aIndex = aInfo[1];
      int bIndex = bInfo[1];

      if (a == b) {
        A.poll();
        B.poll();
        ai = aIndex;
        bi = bIndex;
        result.add(a);
      } else {
        if (a > b) {
          A.poll();
        } else {
          B.poll();
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(result.size()).append('\n');
    for (int i : result) {
      sb.append(i).append(' ');
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}