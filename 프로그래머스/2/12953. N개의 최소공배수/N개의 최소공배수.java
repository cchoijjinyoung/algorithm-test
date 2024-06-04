import java.util.*;

class Solution {
    public int solution(int[] arr) {
        
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length - 1; i++) {
            int a = arr[i];
            int b = arr[i + 1];
            
            // 유클리드 호제법
            int gcd = euclidean(b, a);
            arr[i + 1] = arr[i + 1] * arr[i] / gcd;
        }
        return arr[arr.length - 1];
    }
    
    public int euclidean(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return euclidean(b, a % b);
        }
    }
}