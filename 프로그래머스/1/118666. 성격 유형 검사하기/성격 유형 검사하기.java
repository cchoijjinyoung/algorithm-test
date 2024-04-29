import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int[] points = new int[]{0, 3, 2, 1, 0, 1, 2, 3};
        Map<Character, Integer> hm = new HashMap<>();
        char[] mbti = new char[]{'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        
        for (int i = 0; i < mbti.length; i++) {
            hm.put(mbti[i], 0);
        }
        
        for (int i = 0; i < survey.length; i++) {
            char target = survey[i].charAt(0);
            char rival = survey[i].charAt(1);
            int pi = choices[i];
            
            if (pi <= 4) {
                hm.put(target, hm.get(target) + points[pi]);
            } else {
                hm.put(rival, hm.get(rival) + points[pi]);
            }
        }
        
        char R = hm.get('R') >= hm.get('T') ? 'R' : 'T';
        char C = hm.get('C') >= hm.get('F') ? 'C' : 'F';
        char J = hm.get('J') >= hm.get('M') ? 'J' : 'M';
        char A = hm.get('A') >= hm.get('N') ? 'A' : 'N';
        
        char[] result = new char[]{R, C, J, A};
        answer = String.valueOf(result);
        
        return answer;
    }
}