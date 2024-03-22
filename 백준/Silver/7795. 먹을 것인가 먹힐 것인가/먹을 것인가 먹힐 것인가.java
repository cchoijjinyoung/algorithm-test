import java.util.Arrays;
import java.util.Scanner;

// 먹을 것인가 먹힐 것인가
public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();

    while(T-- > 0) {
      // int count = 0;
      int N = sc.nextInt();
      int M = sc.nextInt();

      int[] A = new int[N];
      int[] B = new int[M];

      for (int j = 0; j < N; j++) A[j] = sc.nextInt();
      for (int j = 0; j < M; j++) B[j] = sc.nextInt();

      Arrays.sort(A);
      Arrays.sort(B);

//      for (int j = 0; j < A.length; j++) {
//        for (int k = 0; k < B.length; k++) {
//          if (A[j] > B[k]) count++;
//          else break;
//        }
//      }
      int bi = 0;
      int answer = 0;
      for (int j = 0; j < N; j++) {
        // A 포인터가 진행하면서
        // B 포인터도 진행하는데, A포인터가 가리키는 숫자보다 B포인터가 가리키는 숫자가 크거나 같을때까지 반복
        while (bi < M && B[bi] < A[j]) bi++;
        answer += bi;
      }
      System.out.println(answer);
    }
  }
}