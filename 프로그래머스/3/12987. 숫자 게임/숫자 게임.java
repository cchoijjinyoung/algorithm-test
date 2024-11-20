import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int N = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int Aidx = 0;
        int Bidx = 0;
        
        while (Bidx < N) {
            if (B[Bidx] > A[Aidx]) {
                Aidx++;
                Bidx++;
                answer++;
            } else if (B[Bidx] <= A[Aidx]) {
                Bidx++;
            }
        }
        return answer;
    }
}