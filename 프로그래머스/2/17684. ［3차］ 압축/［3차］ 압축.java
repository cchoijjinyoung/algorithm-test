import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        
        Map<String, Integer> hm = new HashMap<>();
        
        int number = 1;
        for (int i = 'A'; i <= 'Z'; i++) {
            hm.put(String.valueOf((char)i), number++);
        }
        
        int idx = 0;
        while (idx < msg.length()) {
            String word = "";
            String next = word;
            while (idx < msg.length()) {
                next += msg.charAt(idx);
                if (!hm.containsKey(next)) {
                    break;
                } else {
                    word = next;
                    idx++;
                }
            }
            result.add(hm.get(word));
            hm.put(next, number++);
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}