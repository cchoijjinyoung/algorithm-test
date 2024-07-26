import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] seq = new int[26];
        Arrays.fill(seq, -1);
        int[] answer = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            if (seq[s.charAt(i) - 'a'] == -1) {
                answer[i] = -1;
            } else {
                answer[i] = i - seq[s.charAt(i) - 'a'];
            }
            seq[s.charAt(i) - 'a'] = i;
        }
        return answer;
    }
}