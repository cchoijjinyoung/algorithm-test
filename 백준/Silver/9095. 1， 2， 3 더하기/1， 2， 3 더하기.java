import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if (x < 3) {
                System.out.println(x);
                continue;
            } else if (x == 3) {
                System.out.println(4);
                continue;
            }
            int[] A = new int[x  + 1];
            A[0] = 0;
            A[1] = 1;
            A[2] = 2;
            A[3] = 4;

            for (int j = 4; j < A.length; j++) {
                // ex) 5
                // 5를 만드는 법은
                // 4만드는법에 1더하기 + 3만드는법에 2더하기 + 2만드는법에 3더하기로 표현가능
                A[j] = A[j - 1] + A[j - 2] + A[j - 3];
            }
            System.out.println(A[x]);
        }
    }
}