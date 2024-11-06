import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int L = 0;
        int R = 200_000_000;
        
        while (L <= R) {
            int mid = (L + R) / 2;
            int count = 0;
            
            for (int value : stones) {
                if (value < mid) {
                    count++;
                    if (count >= k) {
                        break;
                    }
                } else {
                    count = 0;
                }
            }
            
            if (count >= k) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return L - 1;
    }
}