import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] array) {
        int answer = 0;
        Map<Integer, Integer> hm = new HashMap<>();
         
        for (int num : array) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        
        int max = -1;
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int value = entry.getValue();
            if (value > max) {
                max = value;
                answer = entry.getKey();
            }
        }
        
        int repeat = 0;
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if (entry.getValue() == max) {
                repeat++;
            }
        }
        
        if (repeat > 1) {
            answer = -1;
        }
        return answer;
    }
}