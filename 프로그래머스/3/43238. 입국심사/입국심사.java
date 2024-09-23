import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long max = (long)n * times[0];
        
        long left = 0;
        long right = max;
        while (left <= right) {
            long mid = right - (right - left) / 2;
            
            long cnt = 0;
            boolean bigger = false;
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
                if (cnt >= n) {
                    bigger = true;
                    break;
                }
            }
            if (bigger) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}