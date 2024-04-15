
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

// 유레카 이론
public class Main {

  public static void main(String[] args) {
    int[] triangles = new int[50];

    for (int i = 1; i < triangles.length; i++) {
      triangles[i] = sumOfTriangles(i);
    }

    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    int[] ans = new int[T];

    for (int t = 0; t < T; t++) {
      int K = sc.nextInt();

      loop:
      for (int i = 1; i < triangles.length; i++) {
        if (triangles[i] >= K) break;
        for (int j = 1; j < triangles.length; j++) {
          if (triangles[j] >= K) break;
          for (int k = 1; k < triangles.length; k++) {
            if (triangles[k] >= K) break;
            int sum = triangles[i] + triangles[j] + triangles[k];
            if (sum == K) {
              ans[t] = 1;
              break loop;
            }
          }
        }
      }
    }
    for (int i = 0; i < ans.length; i++) {
      System.out.println(ans[i]);
    }
  }

  public static int sumOfTriangles(int n) {
    return n * (n + 1) / 2;
  }
}
