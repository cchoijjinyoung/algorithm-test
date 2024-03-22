import java.util.Arrays;
import java.util.Scanner;

// 먹을 것인가 먹힐 것인가
public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    for (int i = 0; i < T; i++) {
      int count = 0;
      int N = sc.nextInt();
      int M = sc.nextInt();

      int[] A = new int[N];
      int[] B = new int[M];

      for (int j = 0; j < N; j++) A[j] = sc.nextInt();
      for (int j = 0; j < M; j++) B[j] = sc.nextInt();

      Arrays.sort(A);
      Arrays.sort(B);

      for (int j = 0; j < A.length; j++) {
        for (int k = 0; k < B.length; k++) {
          if (A[j] > B[k]) count++;
          else break;
        }
      }
      System.out.println(count);
    }
  }
}