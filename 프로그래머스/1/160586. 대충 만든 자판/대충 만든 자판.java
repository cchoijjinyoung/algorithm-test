import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < keymap.length; i++) {
            char[] c = keymap[i].toCharArray();
            for (int j = 0; j < c.length; j++) {
                int value = hm.getOrDefault(c[j], -1);
                if (j + 1 < value) {
                    hm.put(c[j], j + 1);
                } else if (value == -1) {
                    hm.put(c[j], j + 1);
                }
            }
        }
        
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            for (char c : targets[i].toCharArray()) {
                int value = hm.getOrDefault(c, 0);
                if (value == 0) {
                    answer[i] = -1;
                    break;
                }
                answer[i] += value;
            }
        }
        return answer;
    }
}