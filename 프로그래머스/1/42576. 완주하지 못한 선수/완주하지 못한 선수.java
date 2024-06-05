import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> hm = new HashMap<>();
        
        for (String part : participant) {
            hm.put(part, hm.getOrDefault(part, 0) + 1);
        }
        
        for (String comp : completion) {
            hm.put(comp, hm.getOrDefault(comp, 0) - 1);
        }
        
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (entry.getValue() > 0) {
                answer = entry.getKey();
                break;
            }
        }
        return answer;
    }
}