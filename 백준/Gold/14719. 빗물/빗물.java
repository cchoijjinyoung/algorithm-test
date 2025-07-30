
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int H, W;
  static int[] arr;
  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    arr = new int[W];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < W; i++) {
      int h = Integer.parseInt(st.nextToken());
      arr[i] = h;
    }
  }

  static void pro() {
    int result = 0;
    int[] leftMax = new int[W];
    int[] rightMax = new int[W];

    leftMax[0] = arr[0];
    rightMax[W - 1] = arr[W - 1];

    for (int i = 1; i < W; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
    }

    for (int i = W - 2; i >= 0; i--) {
      rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
    }

    for (int i = 0; i < W; i++) {
      result += Math.min(leftMax[i], rightMax[i]) - arr[i];
    }
    System.out.println(result);
  }

  public static void main(String[] args) throws Exception {
    input();
    pro();
  }
}