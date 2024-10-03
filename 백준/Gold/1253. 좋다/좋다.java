import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] A;
    public static void main(String[] args) throws Exception {
        int answer = 0;
        
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
        }
        
        Arrays.sort(A, 1, N + 1);
        
        for (int i = 1; i <= N; i++) {
            if (check(i)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
    
    static boolean check(int targetIdx) {
        int L = 1; int R = N;
        int target = A[targetIdx];
        while (L < R) {
            if (L == targetIdx) L++;
            else if (R == targetIdx) R--;
            else {
                if (A[L] + A[R] == target) return true;
                if (A[L] + A[R] > target) R--;
                else L++;
            }
        }
        return false;
    }
}