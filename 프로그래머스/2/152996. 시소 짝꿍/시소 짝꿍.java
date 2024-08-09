import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Arrays.sort(weights);
        
        int prev = 0;
        for (int i = 0; i < weights.length - 1; i++) {
            int left = weights[i];
            if (i > 0 && left == weights[i - 1]) {
                prev--;
                answer += prev;
                continue;
            }
            
            int j = findRight(weights, left, i);
            prev = 0;
            for (; j > i; j--) {
                int right = weights[j];
                
                if (left == right) {
                    prev++;
                } else if (left * 4 == right * 3) {
                    prev++;
                } else if (left * 3 == right * 2) {
                    prev++;
                } else if (left * 2 == right) {
                    prev++;
                }
            }    
            answer += prev;
        }
        
        return answer;
    }
    
    public int findRight(int[] weights, int num, int i) {
        int left = i;
        int right = weights.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (weights[mid] > num * 2) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}