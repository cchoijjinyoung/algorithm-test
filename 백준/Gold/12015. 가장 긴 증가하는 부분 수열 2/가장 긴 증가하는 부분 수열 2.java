
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] arr;
  static List<Integer> LIS = new ArrayList<>();
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    arr = new int[N];
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(st.nextToken());
      arr[i] = num;
    }
  }

  static void pro() {
    LIS.add(arr[0]);

    for (int i = 1; i < N; i++) {
      int cur = arr[i];

      int lastIdx = LIS.size() - 1;
      if (cur > LIS.get(lastIdx)) {
        LIS.add(cur);
      } else {
        int replaceIdx = binarySearch(cur);
        LIS.set(replaceIdx, cur);
      }
    }
    System.out.println(LIS.size());
  }

  static int binarySearch(int number) {
    int L = 0;
    int R = LIS.size() - 1;
    while (L < R) {
      int mid = (L + R) / 2;
      if (LIS.get(mid) < number) {
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