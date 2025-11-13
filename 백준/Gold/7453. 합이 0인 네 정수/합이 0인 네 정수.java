
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] A, B, C, D;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    A = new int[N];
    B = new int[N];
    C = new int[N];
    D = new int[N];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      A[i] = Integer.parseInt(st.nextToken());
      B[i] = Integer.parseInt(st.nextToken());
      C[i] = Integer.parseInt(st.nextToken());
      D[i] = Integer.parseInt(st.nextToken());
    }
  }

  static void pro() {
    int[] AB = new int[N * N];
    int[] CD = new int[N * N];
    int idx = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        AB[idx] = A[i] + B[j];
        CD[idx] = C[i] + D[j];
        idx++;
      }
    }

    Arrays.sort(AB);
    Arrays.sort(CD);

    long count = 0;
    for (int num : AB) {
      int target = -num;
      int lower = lowerBound(CD, target);
      int upper = upperBound(CD, target);
      count += (upper - lower);
    }

    System.out.println(count);
  }

  static int lowerBound(int[] arr, int target) {
    int L = 0;
    int R = arr.length;
    while (L < R) {
      int mid = (L + R) / 2;
      if (arr[mid] < target) {
        L = mid + 1;
      } else {
        R = mid;
      }
    }
    return L;
  }

  static int upperBound(int[] arr, int target) {
    int L = 0;
    int R = arr.length;
    while (L < R) {
      int mid = (L + R) / 2;
      if (arr[mid] <= target) {
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