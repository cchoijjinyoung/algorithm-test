import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
            int cur = prices[i];
            int cnt = 0;
            for (int j = i + 1; j < prices.length; j++) {
                int target = prices[j];
                if (cur > target) {
                    answer[i] = cnt;
                    cnt++;
                    break;
                } else {
                    cnt++;
                }
            }
            answer[i] = cnt;
        }
        
        return answer;
    }
}