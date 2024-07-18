import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> hs = new HashSet<>();
        
        hs.add(words[0]);
        
        int beforeSize = 1;
        String beforeWord = words[0];
        for (int i = 1; i < words.length; i++) {
            if (words[i].charAt(0) != beforeWord.charAt(beforeWord.length() - 1)) {
                answer[0] = (i + 1) % n;
                if (answer[0] == 0) {
                    answer[0] = n;
                }
                answer[1] = i / n + 1;
                break;
            }
            hs.add(words[i]);
            if (hs.size() == beforeSize) {
                answer[0] = (i + 1) % n;
                if (answer[0] == 0) {
                    answer[0] = n;
                }
                answer[1] = i / n + 1;
                break;
            }
            beforeSize++;
            beforeWord = words[i];
        }

        return answer;
    }
}