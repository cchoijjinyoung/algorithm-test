
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] arr;
  static int[] seq;
  static List<Integer> lis = new ArrayList<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st;
    N = Integer.parseInt(br.readLine());
    arr = new int[N];
    seq = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    lis.add(arr[0]);

    for (int i = 1; i < N; i++) {
      int cur = arr[i];

      int last = lis.get(lis.size() - 1);

      // 이번 애가 lis 라스트보다 크면,
      if (cur > last) {
        lis.add(cur);
        seq[i] = lis.size() - 1;
      } else {
        int replaceIdx = lowerBound(cur);
        lis.set(replaceIdx, cur);
        seq[i] = replaceIdx;
      }
    }
    StringBuilder sb = new StringBuilder();
    sb.append(lis.size()).append('\n');

    Stack<Integer> stack = new Stack<>();
    int idx = lis.size() - 1;
    for (int i = N - 1; i >= 0; i--) {
      if (seq[i] == idx) {
        stack.push(arr[i]);
        idx--;
      }
    }

    while (!stack.isEmpty()) {
      sb.append(stack.pop()).append(' ');
    }
    System.out.println(sb);
  }

  static int lowerBound(int num) {
    int L = 0;
    int R = lis.size() - 1;
    while (L < R) {
      int mid = (L + R) / 2;

      if (lis.get(mid) < num) {
        L = mid + 1;
      } else {
        R = mid;
      }
    }
    return L;
  }


  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}